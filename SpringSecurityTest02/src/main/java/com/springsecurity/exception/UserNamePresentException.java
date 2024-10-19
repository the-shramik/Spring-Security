package com.springsecurity.exception;

public class UserNamePresentException extends Exception{

    public UserNamePresentException(String message) {
        super(message);
    }
}
