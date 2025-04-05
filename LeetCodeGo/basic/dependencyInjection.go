package main

import (
	"fmt"
	"sync"
)

type Container struct {
	services map[string]interface{}
	mu       sync.Mutex
}

var container = &Container{
	services: make(map[string]interface{}),
}

// Register service
func Register(name string, instance interface{}) {
	container.mu.Lock()
	defer container.mu.Unlock()
	container.services[name] = instance
}

// Resolve service
func Resolve(name string) interface{} {
	container.mu.Lock()
	defer container.mu.Unlock()
	return container.services[name]
}

type Repository interface {
	GetData() string
}

// implement interface
type MySQLRepository struct{}

func (db *MySQLRepository) GetData() string {
	return "data from SQL"
}

// service dependency injection
type Service struct {
	repo Repository
}

func BuildService(repo Repository) *Service {
	return &Service{repo: repo}
}

func (svc *Service) Serve() {
	fmt.Println(svc.repo.GetData())
}

func main() {
	Register("MySQL", &MySQLRepository{})
	repo := Resolve("MySQL").(Repository)
	service := BuildService(repo)
	service.Serve()
}
