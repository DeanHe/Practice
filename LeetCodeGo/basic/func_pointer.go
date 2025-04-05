package main

import "fmt"

type Book struct {
	Class byte
	Price int32
}

func modify(book Book) Book {
	fmt.Printf("inner address: %p \n", &book)
	book.Price += 1
	return book
}

func modify1(book *Book) Book {
	fmt.Printf("inner address1: %p \n", book)
	book.Price += 1
	return *book
}

func modify2(book Book) *Book {
	fmt.Printf("inner address2: %p \n", &book)
	book.Price += 1
	return &book
}

func modify3(book *Book) *Book {
	fmt.Printf("inner address3: %p \n", book)
	book.Price += 1
	return book
}

func main() {
	book := Book{1, 200}
	fmt.Printf("init address: %p \n", &book)
	copy := modify(book)
	fmt.Printf("final address: %p \n", &copy)

	book1 := Book{1, 200}
	fmt.Printf("init address1: %p \n", &book1)
	copy1 := modify1(&book1)
	fmt.Printf("final address1: %p \n", &copy1)

	book2 := Book{1, 200}
	fmt.Printf("init address2: %p \n", &book2)
	copy2 := modify2(book2)
	fmt.Printf("final address2: %p \n", &copy2)

	book3 := Book{1, 200}
	fmt.Printf("init address3: %p \n", &book3)
	copy3 := modify3(&book3)
	fmt.Printf("final address3: %p \n", &copy3)

}
