package main

import (
	"bytes"
	"encoding/json"
	"fmt"
	"io"
	"log"
	"os"
)

func main() {
	// write to buffer
	buffer := bytes.Buffer{}
	buffer.Write([]byte("Hello"))
	buffer.WriteString(" World")

	// read from buffer
	line, err := buffer.ReadString('\n')
	if err != nil && err != io.EOF {
		log.Fatal(err)
	}

	fmt.Println(line)
	byteData, err := buffer.ReadByte()

	byteArray := buffer.Bytes()
	str := buffer.String()

	// convert to io.writer
	io.Copy(os.Stdout, &buffer)
	// convert to io.reader
	io.Copy(&buffer, os.Stdin)

	//clear buffer
	buffer.Reset()

	// convert data to json and save in buffer
	encoder := json.NewEncoder(&buffer)
	err = encoder.Encode(byteData)
	if err != nil {
		log.Fatal(err)
	}

	// decode the json from buffer
	decoder := json.NewDecoder(&buffer)
	err = decoder.Decode(byteData)
	if err != nil {
		log.Fatal(err)
	}
}

func Conac

0 3
2 4
3 5
4 5
4 5

0 3
3 4
4 5
5 5
5 5