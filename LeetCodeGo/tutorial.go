package main

import (
	"bytes"
	"fmt"
	"log"
	"math"
	"sync"
)

type I interface {
	printMethod()
}

type T struct {
	str string
}

type Doctor struct {
	number     int
	actor      string
	companions []string
}

func (doc *Doctor) heal() (bool, error) {
	doc.number -= 1
	return true, nil
}

func (t *T) printMethod() {
	fmt.Println(t.str)
}

type F float64

func (f F) printMethod() {
	fmt.Println(f)
}

// channel
var wg = sync.WaitGroup{}
var doneCh = make(chan struct{})

func main() {
	var a int = 1
	fmt.Println(a)

	var i I

	i = &T{"Hello"}
	describe(i)
	i.printMethod()

	i = F(math.Pi)
	describe(i)
	i.printMethod()

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
	aDoctor := Doctor{
		number: 3,
		actor:  "Jon Deer",
		companions: []string{
			"Liz",
			"Grant",
			"Smith",
		},
	}
	aDoctor.heal()
	fmt.Println(aDoctor.actor)

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
	var doc *Doctor
	doc = &aDoctor
	fmt.Println(doc.actor)

	s := sum(1, 2, 3, 4, 5)
	fmt.Println("The sum is: ", s)

	// interface example
	myInt := IntCounter(0)
	var incrementer Incrementer = &myInt
	for i := 0; i < 10; i++ {
		fmt.Println(incrementer.Increment())
	}

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

	// composed interface example
	var wc WriterCloser = InitBufferedWriterCloser()
	wc.Write([]byte("Hello world!"))
	wc.Close()

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

func describe(i I) {
	fmt.Printf("(%v, %T)\n", i, i)
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

// interface exmaple with composed interfaces and struct
type Writer interface {
	Write([]byte) (int, error)
}

type Closer interface {
	Close() error
}

type WriterCloser interface {
	Writer
	Closer
}

type BufferedWriterCloser struct {
	buffer *bytes.Buffer
}

func (bwc *BufferedWriterCloser) Write(data []byte) (int, error) {
	n, err := bwc.buffer.Write(data)
	if err != nil {
		return 0, err
	}

	v := make([]byte, 8)
	for bwc.buffer.Len() > 8 {
		_, err = bwc.buffer.Read(v)
		if err != nil {
			return 0, err
		}
		_, err = fmt.Println(string(v))
		if err != nil {
			return 0, err
		}
	}

	return n, nil
}

func (bwc *BufferedWriterCloser) Close() error {
	for bwc.buffer.Len() > 0 {
		data := bwc.buffer.Next(8)
		_, err := fmt.Println(string(data))
		if err != nil {
			return err
		}
	}
	return nil
}

func InitBufferedWriterCloser() *BufferedWriterCloser {
	return &BufferedWriterCloser{
		buffer: bytes.NewBuffer([]byte{}),
	}
}

// interface example with int type
type Incrementer interface {
	Increment() int
}

type IntCounter int

func (ic *IntCounter) Increment() int {
	*ic++
	return int(*ic)
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
