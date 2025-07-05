
package com.example.library;

public class BookService {
    private BookRepository bookRepository;

    // Setter Injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void displayBook() {
        Book book = bookRepository.getBook();
        book.displayInfo();
    }
}
