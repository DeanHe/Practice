package main

import (
	"bytes"
	"fmt"
	"math"
)

type Printable interface {
	printMethod()
}

type CustomString struct {
	str string
}

func (cs *CustomString) printMethod() {
	fmt.Println(cs.str)
}

type CustomFloat float64

func (f CustomFloat) printMethod() {
	fmt.Println(f)
}

func describe(a Printable) {
	fmt.Printf("(%v, %T)\n", a, a)
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

func main() {
	var a Printable
	a = &CustomString{"Hello"}

	describe(a)
	a.printMethod()

	a = CustomFloat(math.Pi)
	describe(a)
	a.printMethod()

	// composed interface example
	var wc WriterCloser = InitBufferedWriterCloser()
	wc.Write([]byte("Hello world!"))
	wc.Close()
}
