package main

import (
	"fmt"
	"sync"
	"sync/atomic"
)

var (
	n int32
)

func inc() {
	for i := 0; i < 10000; i++ {
		atomic.AddInt32(&n, 1)
	}
}

func main() {
	wg := sync.WaitGroup{}
	wg.Add(2)
	go func() {
		defer wg.Done()
		inc()
	}()
	go func() {
		defer wg.Done()
		inc()
	}()
	wg.Wait()
	fmt.Printf("n = %d\n", n)
}
