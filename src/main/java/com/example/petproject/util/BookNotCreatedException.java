package com.example.petproject.util;

public class BookNotCreatedException extends RuntimeException{
    public BookNotCreatedException(String msg) {
        super(msg);
    }
}
