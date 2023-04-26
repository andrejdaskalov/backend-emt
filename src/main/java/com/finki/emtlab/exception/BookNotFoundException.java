package com.finki.emtlab.exception;

public class BookNotFoundException extends Exception{
    public BookNotFoundException() {
        super("Invalid Book ID");
    }
}
