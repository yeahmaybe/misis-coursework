package com.misis.coursework.exceptions;

public class NullParameterException extends Exception {

    public NullParameterException(String message) {
        super(message);
        System.out.println(message);
    }
}
