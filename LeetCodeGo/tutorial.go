package main

import (
	"fmt"
	"log"
	"sync"
)

type Doctor struct {
	number     int
	actor      string
	companions []string
}

func (doc *Doctor) heal() (bool, error) {
	doc.number -= 1
	return true, nil
}

// channel
var wg = sync.WaitGroup{}
var doneCh = make(chan struct{})

func main() {
	// map
	dict := make(map[string]int)
	delete(dict, "Georgia")
	val, ok := dict["Ohio"]
	fmt.Println(val, ok)

	for k, v := range dict {
		fmt.Println(k, v)
	}

	for k := range dict {
		fmt.Println(k)
	}

	for _, v := range dict {
		fmt.Println(v)
	}

	// struct
	doctor := Doctor{
		number: 3,
		actor:  "Jon Deer",
		companions: []string{
			"Liz",
			"Grant",
			"Smith",
		},
	}
	doctor.heal()
	fmt.Println(doctor.actor)

	// loop
	for i, j := 0, 0; i < 10; i, j = i+1, j+1 {
		fmt.Println(i, j)
	}
	x := 1
	for x < 5 {
		x++
	}

	str := "hello go!"
	for i, c := range str {
		fmt.Println(i, string(c))
	}

	// pointer
	var doctorPointer *Doctor
	doctorPointer = &doctor
	fmt.Println(doctorPointer.actor)

	// function with variadic parameters
	s := sum(1, 2, 3, 4, 5)
	fmt.Println("The sum is: ", s)

	// empty interface
	var obj interface{} = 0
	switch obj.(type) {
	case int:
		fmt.Println("i is an integer")
	case string:
		fmt.Println("i is a string")
	default:
		fmt.Println("I don't know what i is")
	}

	// channel
	ch := make(chan int)
	wg.Add(2)
	// receiver
	go func(ch <-chan int) {
		i := <-ch
		fmt.Println(i)
		wg.Done()
	}(ch)
	// sender
	go func(ch chan<- int) {
		ch <- 42
		wg.Done()
	}(ch)
	wg.Wait()

	// buffer channel
	bc := make(chan int, 50)
	wg.Add(2)
	// receiver
	go func(ch <-chan int) {
		for i := range ch {
			fmt.Println(i)
		}
		wg.Done()
	}(bc)
	// sender
	go func(ch chan<- int) {
		ch <- 42
		ch <- 17
		close(ch)
		wg.Done()
	}(bc)
	wg.Wait()

	doneCh <- struct{}{}
}

func panicker() {
	fmt.Println("about to panic")
	defer func() {
		if err := recover(); err != nil {
			log.Println("Error:", err)
			panic(err)
		}
	}()
	panic("something went wrong")
	fmt.Println("done panicing")
}

func sum(values ...int) *int {
	res := 0
	for _, v := range values {
		res += v
	}
	return &res
}

func logger() {
	for {
		select {
		case <-doneCh:
			break
		}
	}
}

func condtionVariableExample() {
	vote := 0
	finished := 0
	var mu sync.Mutex
	cond := sync.NewCond(&mu)

	for i := 0; i < 10; i++ {
		go func() {
			mu.Lock()
			defer mu.Unlock()
			vote++
			finished++
			cond.Broadcast()
		}()
	}

	mu.Lock()
	defer mu.Unlock()
	for vote < 5 && finished != 0 {
		cond.Wait()
	}
	if vote >= 5 {
		println("win")
	} else {
		println("lost")
	}
}
