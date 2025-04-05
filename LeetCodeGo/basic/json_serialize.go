package main

import (
	"encoding/json"
	"fmt"
)

type Student struct {
	Name   string
	Age    int
	Gender bool
}

type Class struct {
	Id       string
	Students []Student
}

func main() {
	s1 := Student{"Alex", 10, true}
	s2 := Student{"Triston", 10, true}
	s3 := Student{"Denial", 10, true}
	class := Class{Id: "preSchool1", Students: []Student{s1, s2, s3}}
	bytes, err := json.Marshal(class)
	if err != nil {
		fmt.Println("serialize failed", err)
		return
	}

	str := string(bytes)
	fmt.Println(str)

	var class2 Class
	err = json.Unmarshal(bytes, &class2)
	if err != nil {
		fmt.Println("deserialize failed", err)
		return
	}

	fmt.Printf("%+v\n", class2)
}
