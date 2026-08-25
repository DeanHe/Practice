package main

import (
	"fmt"
	"sync"
	"time"
)

func mapReduce(input_ch <-chan int) <-chan int {
	// distribute tasks to multiple workers
	const workerCount = 3
	workers := make([]<-chan int, workerCount)
	for i := 0; i < workerCount; i++ {
		worker := make(chan int)
		workers[i] = worker

		go func(in_ch <-chan int, out_ch chan int) {
			defer close(out_ch)
			for n := range in_ch {
				// simulate compute
				time.Sleep(10 * time.Millisecond)
				out_ch <- n * n
			}
		}(input_ch, worker)
	}

	// merge all workers results
	return merge(workers...)
}

func merge(input_chs ...<-chan int) <-chan int {
	output_ch := make(chan int)
	var wg sync.WaitGroup

	wg.Add(len(input_chs))
	for _, in_ch := range input_chs {
		go func(in <-chan int) {
			defer wg.Done()
			for num := range in {
				output_ch <- num
			}
		}(in_ch)
	}

	go func() {
		wg.Wait()
		close(output_ch)
	}()

	return output_ch
}

// Demo: process large dataset
func main() {
	input_ch := make(chan int)

	// prepare pipline
	output_ch := mapReduce(input_ch)

	// feed data
	go func() {
		defer close(input_ch)
		for i := 0; i < 100; i++ {
			input_ch <- i
		}
	}()

	// collect result
	for res := range output_ch {
		fmt.Printf("result: %d\n", res)
	}
}
