package com.example;

public class ExceptionThrower {

    public void throwException(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input must not be null");
        }
        System.out.println("Input received: " + input);
    }
}
