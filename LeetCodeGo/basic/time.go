package basic

import (
	"fmt"
	"time"
)

const (
	DATE = "2024-03-01 10:00:00"
)

func main() {
	t0 := time.Now()
	fmt.Println(t0.Unix())
	time.Sleep(50 * time.Millisecond)
	t1 := time.Now()
	diff := t1.Sub(t0)
	fmt.Println(diff.Milliseconds())
	fmt.Println(time.Since(t0).Milliseconds())

	duration := time.Duration(2 * time.Second)
	t2 := t0.Add(duration)
	fmt.Println(t2.Unix())

	fmt.Println(t0.Format(DATE))
	str := t0.Format(DATE)
	t3, _ := time.Parse(DATE, str)
	fmt.Println(t3.Unix())

	loc, _ := time.LoadLocation("Asia/Shanghai")
	t4, _ := time.ParseInLocation(DATE, str, loc)
	fmt.Println(t4.Unix())
}
