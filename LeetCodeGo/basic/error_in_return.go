package basic

import (
	"errors"
	"fmt"
)

func divide(a, b int) (int, error) {
	if b == 0 {
		return 0, errors.New("dividen is 0")
	}
	return a / b, nil
}

func main() {
	m, n := 4, 0
	if res, err := divide(m, n); err == nil {
		fmt.Println("result is: ", res)
	} else {
		fmt.Println("error: ", err)
	}
}
