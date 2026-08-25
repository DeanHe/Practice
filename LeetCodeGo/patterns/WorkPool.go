package main

import (
	"context"
	"errors"
	"fmt"
	"sync"
	"time"
)

type WorkPool struct {
	workerCount int
	jobQueue    chan Job
	wg          sync.WaitGroup
}

type Job struct {
	ID   int
	Data string
}

func NewWorkerPool(workerCount int, queueSize int) *WorkPool {
	return &WorkPool{
		workerCount: workerCount,
		jobQueue:    make(chan Job, queueSize),
	}
}

func (wp *WorkPool) Worker(ctx context.Context, id int) {
	defer wp.wg.Done()

	for {
		select {
		case job, ok := <-wp.jobQueue:
			if !ok {
				fmt.Printf("Woker %d: is closed\n", id)
				return
			}

			fmt.Printf("Worker %d: start process task %d\n", id, job.ID)
			// simulate work
			time.Sleep(100 * time.Microsecond)
		case <-ctx.Done():
			fmt.Printf("Woker %d: Context is cancelled\n", id)
			return
		}
	}
}

func (wp *WorkPool) Start(ctx context.Context) {
	for i := 0; i < wp.workerCount; i++ {
		wp.wg.Add(1)
		go wp.Worker(ctx, i)
	}
}

func (wp *WorkPool) Submit(job Job) error {
	select {
	case wp.jobQueue <- job:
		return nil
	default:
		return errors.New("task queue is full")
	}
}

func (wp *WorkPool) Close() {
	close(wp.jobQueue)
	wp.wg.Wait()
}

// Demo: multiple works pull from the same JobQueue(chan)
func main() {
	ctx, cancel := context.WithTimeout(context.Background(), 10*time.Second)
	defer cancel()

	pool := NewWorkerPool(5, 100)
	pool.Start(ctx)

	// create tasks
	for i := 0; i < 20; i++ {
		job := Job{ID: i, Data: fmt.Sprintf("data-%d", i)}
		if err := pool.Submit(job); err != nil {
			fmt.Printf("submit task failed: %v\n", err)
		}
	}

	time.Sleep(2 * time.Second)
}
