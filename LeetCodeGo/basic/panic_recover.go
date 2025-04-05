package basic

import (
	"fmt"
	"time"
)

func problem() {
	defer func() {
		error := recover()
		if error != nil {
			fmt.Printf("panic occurs %s\n", error)
		}
	}()
	a, b := 3, 0
	_ = a / b
	fmt.Println("problem func finish")
}

func main() {
	go problem()
	time.Sleep(2 * time.Second)
	fmt.Println("main func finish")
}
