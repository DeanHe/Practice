package main

import (
	"fmt"
	"sync/atomic"
	"time"
)

var (
	concurrent       int32
	concurrent_limit = make(chan struct{}, 10)
)

func readDB() string {
	atomic.AddInt32(&concurrent, 1)
	fmt.Printf("read DB concurrent %d\n", atomic.LoadInt32(&concurrent))
	time.Sleep(200 * time.Millisecond)
	atomic.AddInt32(&concurrent, -1)
	return "OK"
}

func request() {
	concurrent_limit <- struct{}{}
	readDB()
	<-concurrent_limit
	return
}

func main() {
	for i := 0; i < 100; i++ {
		go request()
	}
	time.Sleep(3 * time.Second)
}
