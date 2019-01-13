package com.hibernatetutorial.demo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * ParsingDateException
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ParsingDateException extends Exception {
    public ParsingDateException(String message) {
        super(String.format("Parsing Date is dd-MM-yyyy ('%s')", message));
    }

}