package main

import (
	"context"
	"fmt"
	student_service "module/lib/my_proto"
	"net"
	"strconv"

	"github.com/redis/go-redis/v9"
	"google.golang.org/grpc"
)

type StudentServer struct {
}

func GetStudentInfoFromRedis(studentId string) student_service.Student {
	client := redis.NewClient(&redis.Options{
		Addr:     "127.0.0.1:6379",
		Password: "",
		DB:       0,
	})
	ctx := context.TODO()
	student := student_service.Student{}
	for field, value := range client.HGetAll(ctx, "Alex").Val() {
		if field == "Name" {
			student.Name = value
		} else if field == "Age" {
			age, err := strconv.Atoi(value)
			if err == nil {
				student.Age = int32(age)
			}
		} else if field == "Height" {
			height, err := strconv.ParseFloat(value, 32)
			if err == nil {
				student.Height = float32(height)
			}
		}
	}
	return student
}

func (server *StudentServer) GetStudentInfo(ctx context.Context, request *student_service.Request) (*student_service.Student, error) {
	defer func() {
		if err := recover(); err != nil {
			fmt.Printf("interface implementation error %v\n", err)
		}
	}()
	studentId := request.StudentId
	student := GetStudentInfoFromRedis(studentId)
	return &student, nil
}

// cmd: protoc --go_out=. --go-grpc_opt=require_unimplemented_servers=false --go-grpc_out=. --proto_path=./lib -I=./lib student_service.proto
// go run ./
func main() {
	//set up redis
	client := redis.NewClient(&redis.Options{
		Addr:     "127.0.0.1:6379",
		Password: "",
		DB:       0,
	})
	ctx := context.TODO()
	err := client.HSet(ctx, "Student1", "Name", "Alex Wang", "Age", 18, "Height", 187.5).Err()
	if err != nil {
		fmt.Printf("redis HSet error %v\n", err)
	}

	listener, err := net.Listen("tcp", ":2346")
	if err != nil {
		panic(err)
	}
	server := grpc.NewServer()
	student_service.RegisterStudentServiceServer(server, new(StudentServer))
	fmt.Println("grpc server is ready.")
	err = server.Serve(listener)
	if err != nil {
		panic(err)
	}
}
