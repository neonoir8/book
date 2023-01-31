package com.example.petproject.services.exception;

public class BookNotCreatedException extends RuntimeException{
    public BookNotCreatedException(String msg) {
        super(msg);
    }
}
