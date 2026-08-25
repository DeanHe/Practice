package main

import (
	"errors"
	"fmt"
	"net/http"
	"sync"
	"time"
)

type CircuitBreaker struct {
	maxFailures int
	resetTime   time.Duration

	mu           sync.RWMutex
	failures     int
	lastFailTime time.Time
	state        string // "closed", "open", "half-open"
}

func NewCircuitBreaker(maxFailures int, resetTime time.Duration) *CircuitBreaker {
	return &CircuitBreaker{
		maxFailures: maxFailures,
		resetTime:   resetTime,
		state:       "closed",
	}
}

func (cb *CircuitBreaker) call(fn func() error) error {
	cb.mu.RLock()
	state := cb.state
	lastFailTime := cb.lastFailTime
	cb.mu.RUnlock()

	// attemp to close cb
	if state == "open" && time.Since(lastFailTime) > cb.resetTime {
		cb.mu.Lock()
		cb.state = "half-open"
		cb.mu.Unlock()
		state = "half-open"
	}

	// fail fast if cb is open
	if state == "open" {
		return errors.New("Circuit Breaker is open")
	}

	// run fn
	err := fn()

	cb.mu.Lock()
	defer cb.mu.Unlock()

	// if fn run failed, decide whether to open cb
	if err != nil {
		cb.failures++
		cb.lastFailTime = time.Now()
		if cb.failures >= cb.maxFailures {
			cb.state = "open"
		}
		return err
	}

	// if fn run success, reset cb
	if cb.state == "half-open" || cb.failures > 0 {
		cb.failures = 0
		cb.state = "closed"
	}

	return nil
}

// Demo
func callExternalAPI() {
	cb := NewCircuitBreaker(3, 30*time.Second)
	err := cb.call(func() error {
		resp, err := http.Get("https://flaky-api.example.com/data")
		if err != nil {
			return err
		}

		defer resp.Body.Close()

		if resp.StatusCode >= 500 {
			return errors.New("Server failure")
		}

		return nil
	})

	if err != nil {
		fmt.Printf("Call External API failed: %v\n", err)
	}
}
