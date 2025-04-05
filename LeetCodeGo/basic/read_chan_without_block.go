package main

import (
	"fmt"
	"time"
)

func readChanWithoutBlock() {
	ch := make(chan int)

	go func() {
		for i := 0; i < 100; i++ {
			ch <- i
			time.Sleep(time.Second)
		}
	}()

	for i := 0; i < 100; i++ {
		select {
		case data := <-ch:
			fmt.Println(data)
		default:
		}
		fmt.Println("pong!")
		time.Sleep(500 * time.Millisecond)
	}
}

func main() {
	readChanWithoutBlock()
}
