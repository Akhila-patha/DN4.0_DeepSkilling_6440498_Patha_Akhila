package com.library;
import org.springframework.stereotype.Component;
@Component
public class BookService {
    private Logger logger; // Constructor injection
    private BookRepository bookRepository; // Setter injection

    public BookService(Logger logger) {
        this.logger = logger;
    }

    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void displayService() {
        logger.log("BookService initialized.");
        bookRepository.displayRepository();
    }
}