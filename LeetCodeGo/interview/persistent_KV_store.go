package main

import (
	"bufio"
	"crypto/sha256"
	"encoding/binary"
	"encoding/json"
	"errors"
	"fmt"
	"os"
	"path/filepath"
	"strings"
	"sync"
	"syscall"
)

const (
	// NumShards is the number of shards for the key-value store.
	NumShards = 16

	OpPut = "PUT"
	OpDel = "DELETE"
	OpGet = "GET"
)

var ErrorNotFound = errors.New("Key not found")

// Record is the value stored inside a shard.
//
// Value is kept as a Base64 string. This is safe for arbitrary byte values
// because the input is already Base64 encoded.
type Record struct {
	Value   string `json:"value,omitempty"`
	Deleted bool   `json:"deleted,omitempty"`
}

// Record is the value stored inside a shard.
//
// Value is kept as a Base64 string. This is safe for arbitrary byte values
// because the input is already Base64 encoded.
type WALRecord struct {
	Op      string `json:"op"`
	ShardID int    `json:"shard_id"`
	Key     string `json:"key"`
	Value   string `json:"value,omitempty"`
}

type Store struct {
	dir string

	// Protects mutations within this process.
	mu sync.RWMutex

	// Held for the lifetime of the process.
	// This protects against another process using the same data directory.
	lockFile *os.File
}

func main() {
	scanner := bufio.NewScanner(os.Stdin)
	// Increase Scanner buffer because a value can be up to 1 MiB.
	scanner.Buffer(make([]byte, 64*1024), 2*1024*1024)
	if !scanner.Scan() {
		return
	}
	dataDir := strings.TrimSpace(scanner.Text())
	store, err := OpenStore(dataDir)
	if err != nil {
		fmt.Fprintf(os.Stderr, "failed to open store: %v\n", err)
		os.Exit(1)
	}
	defer store.Close()
	// Process commands
	for scanner.Scan() {
		line := scanner.Text()
		if line == "" {
			continue
		}
		output := store.parseCommand(line)
		fmt.Println(output)
	}
	if err := scanner.Err(); err != nil {
		fmt.Fprintf(os.Stderr, "input error: %v\n", err)
		os.Exit(1)
	}
}

func OpenStore(dir string) (*Store, error) {
	if err := os.MkdirAll(dir, 0755); err != nil {
		return nil, err
	}

	lockPath := filepath.Join(dir, "Lock")
	lockFile, err := os.OpenFile(
		lockPath,
		os.O_CREATE|os.O_RDWR,
		0644)
	if err != nil {
		return nil, err
	}

	// Acquire an exclusive advisory file lock.
	//
	// LOCK_NB means "don't wait". If another process owns the directory,
	// opening the store fails immediately.
	if err := syscall.Flock(
		int(lockFile.Fd()),
		syscall.LOCK_EX|syscall.LOCK_NB,
	); err != nil {
		lockFile.Close()
		return nil, fmt.Errorf("data directory is already locked: %w", err)
	}

	store := &Store{
		dir:      dir,
		lockFile: lockFile,
	}

	// Recover any incomplete WAL records before accepting commands.
	if err := store.recover(); err != nil {
		store.Close()
		return nil, err
	}

	return store, nil
}

func (s *Store) Close() error {
	if s.lockFile == nil {
		return nil
	}

	// Release advisory lock.
	_ = syscall.Flock(int(s.lockFile.Fd()), syscall.LOCK_UN)
	err := s.lockFile.Close()
	s.lockFile = nil
	return err
}

// processCommand parses and executes one command.
func (s *Store) parseCommand(line string) string {
	parts := strings.Fields(line)
	if len(parts) == 0 {
		return "ERROR"
	}
	switch parts[0] {
	case OpPut:
		if len(parts) != 3 {
			return "ERROR"
		}
		key := parts[1]
		value := parts[2]
		if err := s.Put(key, value); err != nil {
			return "ERROR"
		}
		return "OK"
	case OpDel:
		if len(parts) != 2 {
			return "ERROR"
		}
		key := parts[1]
		if err := s.Delete(key); err != nil {
			return "ERROR"
		}
		return "OK"
	case OpGet:
		if len(parts) != 2 {
			return "ERROR"
		}
		key := parts[1]
		value, err := s.Get(key)
		if err != nil {
			if errors.Is(err, ErrorNotFound) {
				return "NOT_FOUND"
			}
			return "ERROR"
		}
		return value
	default:
		return "ERROR"
	}
}

// Put stores a Base64 encoded value.
func (s *Store) Put(key, valueBase64 string) error {
	shardID := getShardID(key)
	op := WALRecord{
		Op:      OpPut,
		ShardID: shardID,
		Key:     key,
		Value:   valueBase64,
	}
	return s.mutate(op)
}

// Delete writes a tombstone.
//
// Even if the key does not exist, the DELETE operation is persisted.
func (s *Store) Delete(key string) error {
	shardID := getShardID(key)
	op := WALRecord{
		Op:      OpDel,
		ShardID: shardID,
		Key:     key,
	}
	return s.mutate(op)
}

func (s *Store) Get(key string) (string, error) {
	shardID := getShardID(key)
	shard, err := s.loadShard(shardID)
	if err != nil {
		return "", err
	}
	record, ok := shard[key]
	if !ok || record.Deleted {
		return "", ErrorNotFound
	}
	return record.Value, nil
}

// mutate performs the mutation protocol:
//
//  1. acquire process mutex
//  2. write WAL
//  3. fsync WAL
//  4. load affected shard
//  5. apply mutation
//  6. atomically replace shard
//  7. clear WAL
//
// The directory-level file lock is already held for the lifetime of the
// process, so no other process can mutate this directory.
func (s *Store) mutate(op WALRecord) error {
	s.mu.Lock()
	defer s.mu.Unlock()
	// 3: persist WAL before touching the shard.
	if err := s.writeWAL(op); err != nil {
		return err
	}
	// 4: load the current shard.
	shard, err := s.loadShard(op.ShardID)
	if err != nil {
		return err
	}
	// 5: apply operation.
	applyOperation(shard, op)
	// 6: atomically replace shard.
	if err := s.saveShardAtomic(op.ShardID, shard); err != nil {
		return err
	}
	// 7: shard is safely updated, so WAL will be cleared
	if err := s.clearWAL(); err != nil {
		return err
	}
	return nil
}

// applyOperation applies a WAL record to an in-memory shard.
//
// Both PUT and DELETE are idempotent.
func applyOperation(shard map[string]Record, op WALRecord) {
	switch op.Op {
	case OpPut:
		shard[op.Key] = Record{
			Value:   op.Value,
			Deleted: false,
		}
	case OpDel:
		shard[op.Key] = Record{
			Value:   op.Value,
			Deleted: true,
		}
	}
}

// writeWAL writes exactly one pending mutation.
//
// The WAL is written to a temporary file and atomically renamed into place.
// Then we fsync the resulting WAL.
//
// This isn't strictly necessary for the simple overwrite case, but it avoids
// leaving a partially-written JSON WAL file if the process dies while writing.
func (s *Store) writeWAL(op WALRecord) error {
	data, err := json.Marshal(op)
	if err != nil {
		return err
	}
	data = append(data, '\n')
	walPath := s.walPath()
	return writeToFileAtomic(walPath, s.dir, data)
}

// recover checks for a WAL left behind by a crashed mutation.
//
// Recovery is safe because PUT and DELETE are idempotent.
func (s *Store) recover() error {
	walPath := s.walPath()
	data, err := os.ReadFile(walPath)
	if err != nil {
		if os.IsNotExist(err) {
			return nil
		}
		return err
	}
	if len(data) == 0 {
		return s.clearWAL()
	}

	var op WALRecord
	if err := json.Unmarshal(data, &op); err != nil {
		return fmt.Errorf("Invalid WAL: %w", err)
	}

	// Reapply the operation.
	shard, err := s.loadShard(op.ShardID)
	if err != nil {
		return err
	}
	applyOperation(shard, op)
	// Atomically rewrite the shard.
	if err := s.saveShardAtomic(op.ShardID, shard); err != nil {
		return err
	}
	// Recovery succeeded.
	return s.clearWAL()
}

// saveShardAtomic serializes a shard and atomically replaces the shard file.
//
//	serialize
//	    ↓
//	shard.tmp
//	    ↓ fsync
//	shard.tmp is durable
//	    ↓ rename
//	shard.json
//
// Therefore a crash cannot leave a partially-written JSON shard.
func (s *Store) saveShardAtomic(shardID int, shard map[string]Record) error {
	data, err := json.MarshalIndent(shard, "", " ")
	if err != nil {
		return err
	}
	data = append(data, '\n')
	shardPath := s.shardPath(shardID)
	return writeToFileAtomic(shardPath, s.dir, data)
}

// clearWAL removes the WAL after the shard update succeeds.
func (s *Store) clearWAL() error {
	err := os.Remove(s.walPath())
	if os.IsNotExist(err) {
		return nil
	}
	if err != nil {
		return err
	}
	// Make the WAL removal durable.
	return syncDirectory(s.dir)
}

// loadShard loads one JSON shard file.
//
// A nonexistent shard means an empty shard.
func (s *Store) loadShard(shardID int) (map[string]Record, error) {
	path := s.shardPath(shardID)
	data, err := os.ReadFile(path)
	if err != nil {
		if os.IsNotExist(err) {
			return make(map[string]Record), nil
		}
		return nil, err
	}
	if len(data) == 0 {
		return make(map[string]Record), nil
	}

	var shard map[string]Record
	if err := json.Unmarshal(data, &shard); err != nil {
		return nil, fmt.Errorf("corrupt shard %d: %w", shardID, err)
	}
	if shard == nil {
		shard = make(map[string]Record)
	}
	return shard, nil
}

func (s *Store) shardPath(shardID int) string {
	return filepath.Join(s.dir, fmt.Sprintf("shard-%02d.json", shardID))
}

func (s *Store) walPath() string {
	return filepath.Join(s.dir, "wal.json")
}

// SHA-256 provides deterministic key -> shard mapping.
func getShardID(key string) int {
	hash := sha256.Sum256([]byte(key))
	// Use the first 8 bytes of the hash to get a uint64 value.
	n := binary.BigEndian.Uint64(hash[:8])
	return int(n % NumShards)
}

// writeToFileAtomic writes data to a temporary file and atomically renames it into place.
//
// This ensures that a crash cannot leave a partially-written file.
func writeToFileAtomic(path string, dir string, data []byte) error {
	tmpPath := path + ".tmp"
	f, err := os.OpenFile(
		tmpPath,
		os.O_CREATE|os.O_WRONLY|os.O_TRUNC,
		0644,
	)
	if err != nil {
		return err
	}
	ok := false
	defer func() {
		_ = f.Close()
		if !ok {
			_ = os.Remove(tmpPath)
		}
	}()
	if _, err := f.Write(data); err != nil {
		return err
	}
	// Ensure temporary shard contents are durable.
	if err := f.Sync(); err != nil {
		return err
	}
	if err := f.Close(); err != nil {
		return err
	}
	// Atomic replacement.
	if err := os.Rename(tmpPath, path); err != nil {
		return err
	}
	ok = true
	// Make rename durable.
	return syncDirectory(dir)
}

// syncDirectory makes directory metadata changes such as rename/remove
// durable on Unix-like systems.
func syncDirectory(dir string) error {
	f, err := os.Open(dir)
	if err != nil {
		return err
	}
	defer f.Close()
	return f.Sync()
}
