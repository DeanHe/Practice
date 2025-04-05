package main

import (
	"fmt"
	"net/http"
)

func getSex() http.Handler {
	return http.HandlerFunc(
		func(w http.ResponseWriter, r *http.Request) {
			w.Write([]byte("Male\n"))
		})
}

func getName() http.Handler {
	return http.HandlerFunc(
		func(w http.ResponseWriter, r *http.Request) {
			w.Write([]byte("Daniel\n"))
		})
}

func WithMiddleware1(next http.Handler) http.Handler {
	return http.HandlerFunc(
		func(w http.ResponseWriter, r *http.Request) {
			w.Write([]byte("From middleware 1\n"))
			next.ServeHTTP(w, r)
		})
}

func WithMiddleware2(next http.Handler) http.Handler {
	return http.HandlerFunc(
		func(w http.ResponseWriter, r *http.Request) {
			w.Write([]byte("From middleware 2\n"))
			next.ServeHTTP(w, r)
		})
}

func main() {
	http.Handle("/sex", getSex())
	http.Handle("/name", getName())
	http.Handle("/middle1sex", WithMiddleware1(getSex()))
	http.Handle("/middle1name", WithMiddleware1(getName()))
	http.Handle("/middle2middle1sex", WithMiddleware2(WithMiddleware1(getSex())))

	if err := http.ListenAndServe("127.0.0.1:1234", nil); err != nil {
		fmt.Println(err)
	}
}
