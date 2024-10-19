package com.springsecurity.Exception;


public class UserNameAlreadyPresentException extends Exception{
    public UserNameAlreadyPresentException(String message) {
        super(message);
    }
}
