package com.example.springsecuritytest05.exception;

public class UserAlreadyPresentException extends Exception{
    public UserAlreadyPresentException(String message) {
        super(message);
    }
}
