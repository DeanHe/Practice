package main

import (
	"fmt"
	"unsafe"
)

func main() {
	var arr [5]int
	arr = [5]int{1, 2, 3, 4, 5}
	fmt.Println(arr)

	// slice allows dynamic size, but array has fixed size
	// slice is a struct with three fields: pointer to the underlying array, length, and capacity
	ls := make([]int, 3, 5)
	ls[0], ls[1], ls[2] = 1, 2, 3
	ls_b := ls // copy by reference, ls_b and ls share the same underlying array
	ls_b[0] = 100
	fmt.Printf("ls=%v ls_b=%v\n", ls, ls_b)
	ls_append := append(ls, 8)
	fmt.Printf("ls=%d ls_append=%d ls=%d ls_append=%d\n", len(ls), len(ls_append), cap(ls), cap(ls_append))
	ls_append = append(ls_append, 8, 8, 8, 8, 8)
	fmt.Printf("ls=%v ls_append=%v\n", ls, ls_append)

}

type Slice struct {
	arr unsafe.Pointer
	len int
	cap int
}
