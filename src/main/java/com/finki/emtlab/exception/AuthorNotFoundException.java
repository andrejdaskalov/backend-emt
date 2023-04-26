package com.finki.emtlab.exception;

public class AuthorNotFoundException extends Exception{
    public AuthorNotFoundException() {
        super("Invalid Author ID");
    }
}
