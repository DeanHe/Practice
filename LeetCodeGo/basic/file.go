package main

import (
	"bufio"
	"fmt"
	"io"
	"os"
)

func main() {
	// Open a file for reading
	file, err := os.Open("a.txt")
	if err != nil {
		panic(err)
	}
	defer file.Close()
	content := make([]byte, 100)
	n, err := file.Read(content)
	if err != nil {
		panic(err)
	}
	fmt.Println(string(content[:n]))
	fmt.Println(content)

	// Open a file for writing
	wfile, err := os.OpenFile("b.txt", os.O_CREATE|os.O_TRUNC|os.O_WRONLY, 0666)
	if err != nil {
		panic(err)
	}
	defer wfile.Close()
	str := "Hello, World!"
	n, err = wfile.Write([]byte(str))
	if err != nil {
		panic(err)
	}
	fmt.Printf("Wrote %d bytes to b.txt\n", n)

	// Use bufio to read the file line by line
	reader := bufio.NewReader(wfile)
	for {
		line, err := reader.ReadString('\n')
		if err != nil {
			if err == io.EOF {
				fmt.Print(line)
				break
			} else {
				panic(err)
			}
		} else {
			fmt.Print(line)
		}
	}

}
