package main

import (
	"fmt"
	"os"
	"time"
)

var (
	text = "Must be something simple, but I cannot seem to figure out. I keep getting no directory error. Thought the Create function is to create a new file?"
)

type BufferedFileWriter struct {
	buffer         []byte
	bufferEndIndex int
	fout           *os.File
}

func NewWriter(fout *os.File, bufferSize int) *BufferedFileWriter {
	return &BufferedFileWriter{
		buffer:         make([]byte, bufferSize),
		bufferEndIndex: 0,
		fout:           fout,
	}
}

func (writer *BufferedFileWriter) Flush() {
	writer.fout.Write(writer.buffer[0:writer.bufferEndIndex])
	writer.bufferEndIndex = 0
}

func (writer *BufferedFileWriter) Write(content []byte) {
	if len(content) >= len(writer.buffer) {
		writer.Flush()
		writer.fout.Write(content)
	} else {
		if writer.bufferEndIndex+len(content) > len(writer.buffer) {
			writer.Flush()
		}
		copy(writer.buffer[writer.bufferEndIndex:], content)
		writer.bufferEndIndex += len(content)
	}
}

func (writer *BufferedFileWriter) WriteString(content string) {
	writer.Write([]byte(content))
}

func WriteWithoutBuffer(path string) {
	fout, err := os.OpenFile(path, os.O_CREATE|os.O_TRUNC|os.O_WRONLY, 0666)
	if err != nil {
		panic(err)
	}
	defer fout.Close()
	for i := 0; i < 100000; i++ {
		fout.WriteString(text)
	}
}

func WriteWithBuffer(path string) {
	fout, err := os.OpenFile(path, os.O_CREATE|os.O_TRUNC|os.O_WRONLY, 0666)
	if err != nil {
		panic(err)
	}
	defer fout.Close()
	writer := NewWriter(fout, 4096)
	for i := 0; i < 100000; i++ {
		writer.WriteString(text)
	}
}

func main() {
	t0 := time.Now()
	WriteWithoutBuffer("write_without_buffer.txt")
	fmt.Printf("write without buffer takes %d ms\n", time.Since(t0).Milliseconds())

	t0 = time.Now()
	WriteWithBuffer("write_with_buffer.txt")
	fmt.Printf("write with buffer takes %d ms\n", time.Since(t0).Milliseconds())
}
