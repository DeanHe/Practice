package main

import (
	"context"
	"fmt"
	student_service "module/lib/my_proto"
	"testing"

	"google.golang.org/grpc"
	"google.golang.org/grpc/credentials/insecure"
)

// cmd: go test -v grpc_test.go -count=1
func TestGrpcService(t *testing.T) {
	connection, err := grpc.Dial("127.0.0.1:2346", grpc.WithTransportCredentials(insecure.NewCredentials()))
	if err != nil {
		fmt.Printf("fail to connect to grpc server: %v\n", err)
		t.Fail()
	}

	defer connection.Close()
	client := student_service.NewStudentServiceClient(connection)
	response, err := client.GetStudentInfo(context.TODO(), &student_service.Request{StudentId: "Alex"})
	if err != nil {
		fmt.Printf("fail to make grpc API call: %v\n", err)
		t.Fail()
	}
	fmt.Printf("Name %s Age %d Height %.1f\n", response.Name, response.Age, response.Height)
}
