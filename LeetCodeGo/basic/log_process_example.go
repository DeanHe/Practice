package basic

import (
	"fmt"
	"time"
)

type LogEntry struct {
	Timestamp time.Time
	Message   string
}

// flush log by batch and intervals
func LogProcess(logCh <-chan LogEntry, batchSize int, flushInterval time.Duration) {
	batch := make([]LogEntry, 0, batchSize)
	ticker := time.NewTicker(flushInterval)
	defer ticker.Stop()

	for {
		select {
		case log, ok := <-logCh:
			if !ok {
				if len(batch) > 0 {
					FlushLogs(batch)
				}
				return
			}
			batch = append(batch, log)
			if len(batch) == batchSize {
				FlushLogs(batch)
				batch = make([]LogEntry, 0, batchSize)
			}
		case <-ticker.C:
			if len(batch) > 0 {
				FlushLogs(batch)
				batch = make([]LogEntry, 0, batchSize)
			}
		}
	}
}

func FlushLogs(logs []LogEntry) {
	fmt.Printf("Flushing %d logs\n", len(logs))
}
