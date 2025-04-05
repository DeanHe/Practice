package main

import (
	"context"
	"fmt"
	"time"
)

func BatchReadWithRange(ch <-chan int) []int {
	var res []int
	for val := range ch {
		res = append(res, val)
	}
	return res
}

func BatchReadWithTimeout(ch <-chan int, batchSize int, timeout time.Duration) ([]int, error) {
	var res []int
	timeout_condition := time.After(timeout)
	for {
		select {
		case val, ok := <-ch:
			if !ok {
				// ch is closed
				return res, nil
			}
			res = append(res, val)
			if len(res) == batchSize {
				return res, nil
			}
		case <-timeout_condition:
			if len(res) > 0 {
				return res, nil
			}
			return nil, fmt.Errorf("timeout waiting for data")
		}
	}
}

func BatchReadWithContext(ctx context.Context, ch <-chan int, batchSize int) ([]int, error) {
	var res []int
	for {
		select {
		case val, ok := <-ch:
			if !ok {
				return res, nil
			}
			res = append(res, val)
			if len(res) == batchSize {
				return res, nil
			}
		case <-ctx.Done():
			if len(res) > 0 {
				return res, nil
			}
			return nil, ctx.Err()
		}
	}
}

func BatchReadnonBlocking(ch <-chan int, batchSize int) []int {
	var res []int
	for i := 0; i < batchSize; i++ {
		select {
		case val, ok := <-ch:
			if !ok {
				return res
			}
			res = append(res, val)
		default:
			return res
		}
	}
	return res
}

// using buffer channel for batch consumer & producer
func Produce(ch chan<- int) {
	defer close(ch)
	for i := 0; i < 100; i++ {
		ch <- i
	}
}

func Process(res []int) {
	fmt.Printf("processed batch : %v\n", res)
}

func Consume(ch <-chan int, batchSize int) {
	res := make([]int, 0, batchSize)
	for val := range ch {
		res = append(res, val)
		if len(res) == batchSize {
			Process(res)
			res = make([]int, 0, batchSize)
		}
	}

	if len(res) > 0 {
		Process(res)
	}
}
