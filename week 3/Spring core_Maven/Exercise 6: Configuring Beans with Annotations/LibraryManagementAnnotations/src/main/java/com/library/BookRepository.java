package com.library;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {
    public String getBook() {
        return "Design Patterns by Gamma et al.";
    }
}
