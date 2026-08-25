package main

import (
	"fmt"
)

func main() {
	ch := make(chan int, 0)
	ch <- 1
	fmt.Println("over")
}
