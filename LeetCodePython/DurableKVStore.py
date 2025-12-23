import os
import json
import threading
from typing import Any, Dict, Optional

class DurableKVStore:
    """
    Simple durable key-value store using an append-only write-ahead log (WAL)
    and periodic snapshotting (compaction).

    ```
    On each `set` and `delete` we append a JSON line to the WAL and fsync it
    so the mutation is durable on disk. On startup we load the latest snapshot
    (if any) then replay WAL entries after the snapshot to reconstruct state.

    Methods:
      - get(key, default=None)
      - set(key, value)
      - delete(key)
      - compact()  -> write snapshot and truncate WAL
      - close()    -> alias for compact to persist snapshot
    """
WAL_FILENAME = "store.log"
SNAPSHOT_FILENAME = "store.snapshot.json"

def __init__(self, directory: str):
    os.makedirs(directory, exist_ok=True)
    self.directory = directory
    self.wal_path = os.path.join(directory, self.WAL_FILENAME)
    self.snapshot_path = os.path.join(directory, self.SNAPSHOT_FILENAME)
    self._lock = threading.RLock()
    self._data: Dict[str, Any] = {}
    open(self.wal_path, "a").close()
    self._load_from_disk()

def _fsync_file(self, f):
    f.flush()
    os.fsync(f.fileno())

def _append_wal(self, record: Dict[str, Any]):
    """Append a JSON line into WAL and fsync to ensure durability."""
    line = json.dumps(record, separators=(",", ":"), ensure_ascii=False) + "\n"
    with open(self.wal_path, "a", encoding="utf-8") as f:
        f.write(line)
        self._fsync_file(f)

def _load_from_disk(self):
    """Load snapshot if exists, then replay WAL."""
    with self._lock:
        if os.path.exists(self.snapshot_path):
            try:
                with open(self.snapshot_path, "r", encoding="utf-8") as f:
                    self._data = json.load(f)
            except Exception as e:
                print("Warning: failed to load snapshot:", e)
                self._data = {}

        try:
            with open(self.wal_path, "r", encoding="utf-8") as f:
                for lineno, raw in enumerate(f, start=1):
                    raw = raw.strip()
                    if not raw:
                        continue
                    try:
                        rec = json.loads(raw)
                    except json.JSONDecodeError:
                        print(f"Warning: skipping malformed WAL line {lineno}")
                        continue
                    op = rec.get("op")
                    key = rec.get("key")
                    if op == "set":
                        self._data[key] = rec.get("value")
                    elif op == "delete":
                        self._data.pop(key, None)
        except FileNotFoundError:
            pass

def get(self, key: str, default: Optional[Any] = None) -> Any:
    with self._lock:
        return self._data.get(key, default)

def set(self, key: str, value: Any):
    """Set a value and append to WAL (value must be JSON-serializable)."""
    rec = {"op": "set", "key": key, "value": value}
    with self._lock:
        self._append_wal(rec)
        self._data[key] = value

def delete(self, key: str):
    rec = {"op": "delete", "key": key}
    with self._lock:
        self._append_wal(rec)
        self._data.pop(key, None)

def compact(self):
    """
    Create a snapshot of current state (atomic write) and truncate WAL.
    """
    with self._lock:
        tmp_path = self.snapshot_path + ".tmp"
        with open(tmp_path, "w", encoding="utf-8") as f:
            json.dump(self._data, f, ensure_ascii=False, indent=2)
            self._fsync_file(f)
        os.replace(tmp_path, self.snapshot_path)
        with open(self.wal_path, "w", encoding="utf-8") as f:
            self._fsync_file(f)

def close(self):
    self.compact()

def keys(self):
    with self._lock:
        return list(self._data.keys())

def items(self):
    with self._lock:
        return list(self._data.items())

def __contains__(self, key):
    with self._lock:
        return key in self._data

"""
# ---- Example usage ----

if **name** == "**main**":
    demo_dir = "durable_kv_demo"
    store = DurableKVStore(demo_dir)
    store.set("foo", {"count": 1})
    store.set("bar", [10, 20])
    store.delete("foo")
    print("Items before compact:", store.items())
    store.compact()


    print("\nReopening store to verify persistence...")
    reopened = DurableKVStore(demo_dir)
    print("Items after reload:", reopened.items())
"""
