package main

import "fmt"

type IntegerFunc func(int, int) int

func (h IntegerFunc) Transform(arg int) int {
	return h(arg, 4) * 2
}

func main() {
	f := func(a int, b int) int {
		return a + b
	}
	var a IntegerFunc = IntegerFunc(f)
	fmt.Printf("%d\n", a(2, 5))
	fmt.Printf("%d\n", a.Transform(3))
}
