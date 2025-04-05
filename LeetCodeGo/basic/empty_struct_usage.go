package basic

import (
	"fmt"
	"time"
	"unsafe"
)

type ES struct {
}

func main() {
	var a ES
	var b struct{}
	// empty struct takes 0 size
	fmt.Printf("a address %p size %d\n", &a, unsafe.Sizeof(a))
	fmt.Printf("b address %p szie %d\n", &b, unsafe.Sizeof(b))

	// create a set by map
	set := make(map[string]struct{}, 10)
	set["Alex"] = ES{}
	set["Bob"] = struct{}{}
	fmt.Println(len(set))

	// use empty struct for goroutines wait signal
	wait := make(chan struct{}, 0)
	go func() {
		time.Sleep(3 * time.Second)
		fmt.Println("after 3 seconds")
		wait <- struct{}{}
	}()

	<-wait
}
