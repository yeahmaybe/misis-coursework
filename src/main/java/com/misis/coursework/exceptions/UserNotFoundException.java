package com.misis.coursework.exceptions;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message) {
        super(message);
        System.out.println(message);
    }
}
