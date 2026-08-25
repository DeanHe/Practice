package main

import (
	"encoding/json"
	"fmt"
	"io/ioutil"
	"net/http"
	"net/url"
	"strings"
	"testing"
)

func TestGetStudentInfoFromRedis(t *testing.T) {
	studentId := "student1"
	student := GetStudentInfoFromRedis(studentId)
	if len(student.Name) == 0 || student.Age <= 0 || student.Height <= 0 {
		t.Fail()
	} else {
		fmt.Printf("student info: %+v\n", student)
	}
}

// go test -v -run TestGetName -count=1
func TestGetName(t *testing.T) {
	resp, err := http.Get("http://127.0.0.1:2345/getName?studentId=student1")
	if err != nil {
		fmt.Printf("http get failed: %v\n", err)
		t.Fail()
	} else {
		defer resp.Body.Close()
		bytes, err := ioutil.ReadAll(resp.Body)
		if err != nil {
			fmt.Printf("read body failed: %v\n", err)
			t.Fail()
		} else {
			fmt.Printf("response: %s\n", string(bytes))
		}
	}
}

// go test -v -run TestGetAge -count=1
func TestGetAge(t *testing.T) {
	resp, err := http.PostForm("http://127.0.0.1:2345/getAge", url.Values{"studentId": []string{"student1"}})
	if err != nil {
		fmt.Printf("http post failed: %v\n", err)
		t.Fail()
	} else {
		defer resp.Body.Close()
		bytes, err := ioutil.ReadAll(resp.Body)
		if err != nil {
			fmt.Printf("read body failed: %v\n", err)
			t.Fail()
		} else {
			fmt.Printf("response: %s\n", string(bytes))
		}
	}
}

// go test -v -run TestGetHeight -count=1
func TestGetHeight(t *testing.T) {
	client := &http.Client{}
	reader := strings.NewReader(`{"studentId": "student1"}`)
	req, err := http.NewRequest("POST", "http://127.0.0.1:2345/getHeight", reader)
	if err != nil {
		fmt.Printf("http post failed: %v\n", err)
		t.Fail()
	} else {
		req.Header.Set("Content-Type", "application/json")
		resp, err := client.Do(req)
		if err != nil {
			fmt.Printf("http post failed: %v\n", err)
			t.Fail()
		} else {
			defer resp.Body.Close()
			bytes, err := ioutil.ReadAll(resp.Body)
			if err != nil {
				fmt.Printf("read body failed: %v\n", err)
				t.Fail()
			} else {
				fmt.Printf("response: %s\n", string(bytes))
			}
		}
	}
}

// go test -v -run TestGetStudent -count=1
func TestGetStudent(t *testing.T) {
	client := &http.Client{}
	reader := strings.NewReader(`{"studentId": "student1"}`)
	req, err := http.NewRequest("POST", "http://127.0.0.1:2345/getStudent", reader)
	if err != nil {
		fmt.Printf("http post failed: %v\n", err)
		t.Fail()
	} else {
		req.Header.Set("Content-Type", "application/json")
		resp, err := client.Do(req)
		if err != nil {
			fmt.Printf("http post failed: %v\n", err)
			t.Fail()
		} else {
			defer resp.Body.Close()
			bytes, err := ioutil.ReadAll(resp.Body)
			if err != nil {
				fmt.Printf("read body failed: %v\n", err)
				t.Fail()
			} else {
				var student Student
				err = json.Unmarshal(bytes, &student)
				if err != nil {
					fmt.Printf("unmarshal failed: %v\n", err)
					t.Fail()
				} else {
					fmt.Printf("response: %+v\n", student)
				}
			}
		}
	}
}
