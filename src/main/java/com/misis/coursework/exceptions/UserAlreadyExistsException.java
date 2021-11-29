package com.misis.coursework.exceptions;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException(String message) {
        super(message);
        System.out.println(message);
    }
}
