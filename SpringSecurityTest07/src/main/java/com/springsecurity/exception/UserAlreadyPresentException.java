package com.springsecurity.exception;

public class UserAlreadyPresentException extends Exception{

    public UserAlreadyPresentException(String message) {
        super(message);
    }
}
