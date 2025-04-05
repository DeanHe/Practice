package main

import (
	"fmt"
	"time"
)

func main() {
	ch := make(chan int, 1)
	timer := time.NewTimer(time.Second)

	go func() {
		time.Sleep(2 * time.Second)
		ch <- 1
		close(ch)
	}()

	select {
	case <-timer.C:
		fmt.Println("time out")
	case result := <-ch:
		fmt.Println(result)
	}
}
