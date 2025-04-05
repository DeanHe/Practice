package main

import (
	"fmt"
	"time"
)

func worker(id int, jobs <-chan int, results chan<- int) {
	for job := range jobs {
		fmt.Printf("Worker: %d, starts job %d\n", id, job)
		time.Sleep(time.Second)
		results <- job * 2
		fmt.Printf("Worker: %d, finishes job %d\n", id, job)
	}
}

func main() {
	const numJobs = 5
	jobs := make(chan int, numJobs)
	results := make(chan int, numJobs)

	// start workers
	for w := 1; w <= 3; w++ {
		go worker(w, jobs, results)
	}

	// trigger jobs
	for j := 0; j < numJobs; j++ {
		jobs <- j
	}
	close(jobs)

	//get results
	for j := 0; j < numJobs; j++ {
		<-results
	}
}
