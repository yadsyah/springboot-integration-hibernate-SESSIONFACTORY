package com.hibernatetutorial.demo.exception;


/**
 * ParsingDateException
 */
public class ParsingDateException extends Exception {


    public ParsingDateException(String message){
        super(String.format("Parsing Date is dd-MM-yyyy ('%s')",message));
    }
    
}