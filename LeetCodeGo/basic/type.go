package main

// alias
type Stack []int

func (s *Stack) Push(num int) {
	*s = append(*s, num)
}

// interface
type Collection interface {
	Push(num int)
}

func main() {
	//s := new(Stack)
	s := make(Stack, 0, 10)
	s.Push(2)

	st := &Stack{}
	st.Push(3)

	var c Collection = &s
	c.Push(1)
}
