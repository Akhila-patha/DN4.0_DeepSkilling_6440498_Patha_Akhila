package com.library.service;

import org.springframework.stereotype.Component;

@Component
public class BookService {
    public void getBookDetails() {
        System.out.println("Book: Clean Code by Robert C. Martin");
    }
}
