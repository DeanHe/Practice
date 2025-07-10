package main

import (
	"fmt"
	"sync"
	"testing"
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

func (bp *BatchProcessor) Flush() {
	bp.mu.Lock()
	defer bp.mu.Unlock()

	if len(bp.buffer) == 0 {
		return
	}

	fmt.Println("Processing batch:", bp.buffer)
	bp.buffer = nil
}

func (bp *BatchProcessor) Start() {
	ticker := time.NewTicker(batchDelay)
	defer ticker.Stop()

	for {
		select {
		case <-bp.flushTrigger:
			bp.Flush()
		case <-ticker.C:
			bp.Flush()
		}
	}
}

func BuildBatchProcessor() *BatchProcessor {
	bp := &BatchProcessor{
		flushTrigger: make(chan struct{}, 1),
	}
	go bp.Start()
	return bp
}

func main() {
	batchProcessor := BuildBatchProcessor()
	for i := 1; i <= 12; i++ {
		batchProcessor.Add(fmt.Sprintf("data-%d", i))
		time.Sleep(time.Duration(i%3) * time.Second) // simulate intermediate input
	}

	time.Sleep(5 * time.Second) // simulate intermediate input
}

func TestBatchFlush(t *testing.T) {
	bp := BuildBatchProcessor()
	bp.Add("a")
	bp.Add("b")
	time.Sleep(150 * time.Millisecond)
	t.Fatal("time out without refresh")
}
