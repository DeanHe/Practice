package main

import (
	"context"
	"fmt"
	"os"
	"time"

	"github.com/redis/go-redis/v9"
)

func kvOperation(ctx context.Context, client *redis.Client) {
	key := "name"
	value := "Alex"
	// 0 means the key does not expire
	err := client.Set(ctx, key, value, 0).Err()
	checkError(err)

	//client.Expire(ctx, key, 2*time.Second)
	time.Sleep(3 * time.Second)

	val, err := client.Get(ctx, key).Result()
	checkError(err)
	fmt.Printf("key: %s, value: %s\n", key, val)

	client.Del(ctx, key)
}

func listOperation(ctx context.Context, client *redis.Client) {
	key := "ids"
	values := []interface{}{1, 2, 3, "blabla"}
	err := client.RPush(ctx, key, values...).Err()
	checkError(err)

	v2, err := client.LRange(ctx, key, 0, -1).Result()
	checkError(err)
	fmt.Printf("key: %s, values: %v\n", key, v2)

	client.Del(ctx, key)
}

func hashtableOperation(ctx context.Context, client *redis.Client) {
	err := client.HSet(ctx, "student1", "Name", "Alex", "Age", 30, "Height", 175).Err()
	checkError(err)
	err = client.HSet(ctx, "student2", "Name", "Bob", "Age", 25, "Height", 180).Err()
	checkError(err)

	for field, value := range client.HGetAll(ctx, "student1").Val() {
		fmt.Printf("student1 %s: %s\n", field, value)
	}
	for field, value := range client.HGetAll(ctx, "student2").Val() {
		fmt.Printf("student2 %s: %s\n", field, value)
	}

	client.Del(ctx, "student1")
	client.Del(ctx, "student2")
}

func checkError(err error) {
	if err != nil {
		if err == redis.Nil {
			fmt.Println("key does not exist")
		} else {
			fmt.Printf("Error: %v\n", err)
			os.Exit(1)
		}
	}
}

/*
func main() {
	client := redis.NewClient(&redis.Options{
		Addr:     "127.0.0.1:6379",
		Password: "",
		DB:       0,
	})
	ctx := context.TODO()
	kvOperation(ctx, client)
	listOperation(ctx, client)
	hashtableOperation(ctx, client)
}
*/
