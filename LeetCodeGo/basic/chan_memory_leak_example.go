package main

import (
	"fmt"
	"sync"
	"time"
)

func main() {
	wg := &sync.WaitGroup{}
	ch := make(chan any, 1)
	arr := []int{1, 2, 3, 4, 5}
	for _, v := range arr {
		j := v
		wg.Add(1)
		go func() {
			ch <- j
		}()
	}

	go func() {
		for data := range ch {
			fmt.Println(data)
			wg.Done()
		}
		fmt.Println("correctly closed")
	}()
	wg.Wait()
	//fix by close channel after wg.Wait()
	close(ch)
	time.Sleep(1 * time.Second)
}
