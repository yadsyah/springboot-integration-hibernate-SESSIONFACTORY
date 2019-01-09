package com.hibernatetutorial.demo.exception;

public class ResourceNotFoundException extends Exception{

    public ResourceNotFoundException(String message) {
        super(String.format("Data not found id : `%s`",message));
    }
}
