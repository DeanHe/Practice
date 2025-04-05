package main

import "net/http"

type Empty struct {
}

func (e *Empty) ServeHTTP(w http.ResponseWriter, r *http.Request) {

}

func EmptyHandler() http.Handler {
	return &Empty{}
}

func BusinessHandler(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		w.Write([]byte("BusinessHandler output\n"))
		next.ServeHTTP(w, r)
	})
}

func MiddlewareHandler(next http.Handler) http.Handler {
	return http.HandlerFunc(func(w http.ResponseWriter, r *http.Request) {
		w.Write([]byte("MiddlewareHandler output\n"))
		next.ServeHTTP(w, r)
	})
}

func main() {
	http.ListenAndServe("127.0.0.1:1234", MiddlewareHandler(BusinessHandler(EmptyHandler())))
}
