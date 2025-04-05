package basic

import (
	"fmt"
	"runtime"
)

func main() {
	memory_size := 10
	slice := make([]int, memory_size)

	for i := 0; i < len(slice); i++ {
		slice[i] = 5
	}

	for i := 0; i <= len(slice); i++ {
		fmt.Printf("%d ", slice[i])
	}
	fmt.Println()

	runtime.GC()
}
