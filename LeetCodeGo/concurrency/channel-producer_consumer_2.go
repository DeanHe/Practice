package main

import (
	"fmt"
)

var buffer = make(chan int, 100)
var producer_finish = make(chan struct{})
var consumer_finish = make(chan struct{})

func produce() {
	for i := 0; i < 10; i++ {
		buffer <- i
	}
	producer_finish <- struct{}{}
}

func consume() {
	for {
		n, ok := <-buffer
		if ok {
			fmt.Printf("receive %d\n", n)
		} else {
			// only when ch is closed and empty
			break
		}
	}
	consumer_finish <- struct{}{}
}

func main() {

	// producers
	producer_cnt := 2
	for i := 0; i < producer_cnt; i++ {
		go produce()
	}

	//consumer
	go consume()

	for i := 0; i < producer_cnt; i++ {
		<-producer_finish
	}

	close(buffer)
	<-consumer_finish
}
