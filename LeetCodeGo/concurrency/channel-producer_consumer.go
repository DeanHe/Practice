package main

import (
	"fmt"
	"sync"
)

func main() {
	ch := make(chan int, 100)

	// producers
	producer_wg := sync.WaitGroup{}
	producer_wg.Add(2)
	go func() {
		defer producer_wg.Done()
		for i := 0; i < 10; i++ {
			ch <- i
		}
	}()
	go func() {
		defer producer_wg.Done()
		for i := 0; i < 10; i++ {
			ch <- i
		}
	}()

	//consumer
	consumer_finish := make(chan struct{})
	go func() {
		sum := 0
		for {
			n, ok := <-ch
			if ok {
				sum += n
			} else {
				// only when ch is closed and empty
				break
			}
		}
		fmt.Printf("sum=%d\n", sum)
		consumer_finish <- struct{}{}
	}()

	producer_wg.Wait()
	close(ch)
	<-consumer_finish
}
