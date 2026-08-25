package main

import (
	"context"
	"fmt"
	"net/http"
	"strconv"

	"github.com/gin-gonic/gin"
	"github.com/redis/go-redis/v9"
)

type Student struct {
	Name   string
	Age    int
	Height float32
}

type Request struct {
	StudentId string `json:"studentId"`
}

func GetStudentInfoFromRedis(studentId string) Student {
	client := redis.NewClient(&redis.Options{
		Addr:     "127.0.0.1:6379",
		Password: "",
		DB:       0,
	})
	ctx := context.TODO()
	student := Student{}
	for field, value := range client.HGetAll(ctx, studentId).Val() {
		if field == "Name" {
			student.Name = value
		} else if field == "Age" {
			age, err := strconv.Atoi(value)
			if err == nil {
				student.Age = age
			}
		} else if field == "Height" {
			height, err := strconv.ParseFloat(value, 10)
			if err == nil {
				student.Height = float32(height)
			}
		}
	}
	return student
}

func GetName(ctx *gin.Context) {
	param := ctx.Query("studentId")
	if len(param) == 0 {
		ctx.String(http.StatusBadRequest, "studentId is required")
		return
	}
	student := GetStudentInfoFromRedis(param)
	ctx.String(http.StatusOK, student.Name)
	return
}

func GetAge(ctx *gin.Context) {
	param := ctx.PostForm("studentId")
	if len(param) == 0 {
		ctx.String(http.StatusBadRequest, "studentId is required")
		return
	}
	student := GetStudentInfoFromRedis(param)
	ctx.String(http.StatusOK, strconv.Itoa(student.Age))
	return
}

func GetHeight(ctx *gin.Context) {
	var param Request
	err := ctx.BindJSON(&param)
	if err != nil {
		ctx.String(http.StatusBadRequest, "invalid request body")
		return
	}
	student := GetStudentInfoFromRedis(param.StudentId)
	ctx.String(http.StatusOK, strconv.FormatFloat(float64(student.Height), 'f', 1, 64))
	return
}

func GetStudent(ctx *gin.Context) {
	var param Request
	err := ctx.BindJSON(&param)
	if err != nil {
		ctx.String(http.StatusBadRequest, "invalid request body")
		return
	}
	student := GetStudentInfoFromRedis(param.StudentId)
	ctx.JSON(http.StatusOK, student)
	return
}

func main() {
	client := redis.NewClient(&redis.Options{
		Addr:     "127.0.0.1:6379",
		Password: "",
		DB:       0,
	})
	ctx := context.TODO()
	err := client.HSet(ctx, "student1", "Name", "Alex Wang", "Age", 18, "Height", 187.5).Err()
	if err != nil {
		fmt.Printf("redis HSet error %v\n", err)
	}
	engine := gin.Default()
	engine.GET("/getName", GetName)
	engine.POST("/getAge", GetAge)
	engine.POST("/getHeight", GetHeight)
	engine.POST("/getStudent", GetStudent)
	err = engine.Run("0.0.0.0:2345")
	if err != nil {
		panic(err)
	}
}
