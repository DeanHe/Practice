package socketprogram

import (
	"fmt"
	"net"
)

func main() {
	listener, err := net.Listen("tcp", ":8080")
	if err != nil {
		fmt.Printf("listen failed: %v\n", err)
		return
	}

	defer listener.Close()
	fmt.Println("Socket established, start listening...")
	for {
		conn, err := listener.Accept()
		if err != nil {
			fmt.Printf("accept connection failed: %v\n", err)
			continue
		}

		go handleConnection(conn)
	}
}

func handleConnection(conn net.Conn) {
	defer conn.Close()
	buffer := make([]byte, 1024)
	bytes, err := conn.Read(buffer)
	if err != nil {
		fmt.Printf("read failed: %v\n", err)
		return
	}

	fmt.Printf("read in Data: %s\n", string(buffer[:bytes]))
	conn.Write([]byte("Hello from server"))
}
