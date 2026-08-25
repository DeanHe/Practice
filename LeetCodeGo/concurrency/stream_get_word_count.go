package main

import (
	"strings"
	"sync"
	"time"
)

type stream interface {
	Read(n int) ([]byte, error)
}

type wordCounter struct {
	stream  stream
	mu      sync.RWMutex
	counts  map[string]int
	partial strings.Builder
}

func NewWordCounter(s stream) *wordCounter {
	wc := &wordCounter{
		stream: s,
		counts: make(map[string]int),
	}
	go wc.ReadLoop()
	return wc
}

func (wc *wordCounter) ReadLoop() {
	for {
		data, err := wc.stream.Read(1024)
		if len(data) > 0 {
			wc.process(data)
		}
		if err != nil {
			// Retry with backoff in production.
			time.Sleep(100 * time.Millisecond)
			continue
		}
	}
}

func (wc *wordCounter) process(data []byte) {
	wc.mu.Lock()
	defer wc.mu.Unlock()

	for _, b := range data {
		if b == ' ' {
			if wc.partial.Len() > 0 {
				word := wc.partial.String()
				wc.counts[word]++
				wc.partial.Reset()
			}
		} else {
			wc.partial.WriteByte(b)
		}
	}
}

func (wc *wordCounter) GetWordCount() map[string]int {
	wc.mu.RLock()
	defer wc.mu.RUnlock()
	result := make(map[string]int)
	for k, v := range wc.counts {
		result[k] = v
	}
	return result
}
