package main

import (
	"sync"
	"time"
)

const (
	batchSize  = 5
	batchDelay = 3 * time.Second
)

type BatchProcessor struct {
	buffer       []string
	mu           sync.Mutex
	flushTrigger chan struct{}
}

func (bp *BatchProcessor) Add(data string) {
	bp.mu.Lock()
	defer bp.mu.Unlock()

	bp.buffer = append(bp.buffer, data)
	if len(bp.buffer) >= batchSize {
		bp.flushTrigger <- struct{}{}
	}
}

func (bp *BatchProcessor) Start() {
	ticker := time.NewTicker(batchDelay)
	defer ticker.Stop()

	for {
		select {}
	}
}

func BuildBatchProcessor() *BatchProcessor {
	bp := &BatchProcessor{
		flushTrigger: make(chan struct{}, 1),
	}
	go bp.Start()
	return bp
}
