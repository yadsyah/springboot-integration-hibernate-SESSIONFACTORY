package com.hibernatetutorial.demo.utility;

import com.hibernatetutorial.demo.payload.response.global.DataApiResponse;
import org.slf4j.Logger;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class ValidationUtil {

    public static List<String> fromBindingErrors(Errors errors) {
        List<String> validErrors = new ArrayList<String>();

        for (ObjectError objectError : errors.getAllErrors()) {
            validErrors.add(objectError.getDefaultMessage());
        }
        return validErrors;
    }

    public static List<String> fromBindingErrorsClassPath(Errors errors){
        List<String> validErrors = new ArrayList<String>();
        for (ObjectError objectError:errors.getAllErrors()) {
            validErrors.add(objectError.getCodes().getClass().getAnnotatedSuperclass().getClass().toString());
        }
        return validErrors;
    }

    public static List<String> fieldValidation(Errors errors){
        List<String> validErrors = new ArrayList<String>();

        for (ObjectError objectError : errors.getAllErrors()) {
            validErrors.add(objectError.getDefaultMessage());
        }
        return validErrors;
    }
}
