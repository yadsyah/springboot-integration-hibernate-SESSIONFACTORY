package com.hibernatetutorial.demo.controller;

import com.hibernatetutorial.demo.payload.response.global.DataApiResponse;
import com.hibernatetutorial.demo.payload.response.global.ErrorDetails;
import com.hibernatetutorial.demo.payload.response.global.FieldErrorDTO;
import com.hibernatetutorial.demo.utility.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@ControllerAdvice
public class ExceptionHandlingController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlingController.class);

    @ExceptionHandler(NoResultException.class)
    public final ResponseEntity<ErrorDetails> resourceNotFound(NoResultException ex, WebRequest req) {
        ErrorDetails errorDetails = new ErrorDetails(new Date().getTime(), ex.getMessage(), false, req.getDescription(false));
        return new ResponseEntity(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ErrorDetails> invalidInput(MethodArgumentNotValidException ex) {
        StringBuilder sb = new StringBuilder();
        BindingResult bindingResult = ex.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        List<FieldError> fieldErrorsList = bindingResult.getFieldErrors();
        List<FieldErrorDTO> fieldErrors = new ArrayList<>();
        for (FieldError field:fieldErrorsList) {
            fieldErrors.add(new FieldErrorDTO(field.getField(),field.getDefaultMessage()));
        }
        String field = fieldError.getField();
        sb.append("Error Count : " + bindingResult.getErrorCount()).append("|");
        sb.append("Message : " + ValidationUtil.fromBindingErrors(bindingResult)).append("|");
        sb.append("Field : " + field).append("|");
        sb.append("Target : " + bindingResult.getTarget());
        String messageError = sb.toString();
        return new ResponseEntity(new ErrorDetails(new Date().getTime(), "Validation Field", false, messageError, fieldErrors), HttpStatus.BAD_REQUEST);
    }
}
