package com.hibernatetutorial.demo.payload.response.global;


import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorDetails {

    private Long timestamp;
    private String message;
    private Boolean success;
    private String details;
    private List<FieldErrorDTO> listField;
    public ErrorDetails() {
    }

    public ErrorDetails(Long timestamp, String message, Boolean success, String details) {
        this.timestamp = timestamp;
        this.message = message;
        this.success = success;
        this.details = details;
    }

    public ErrorDetails(Long timestamp, String message, Boolean success, String details, List<FieldErrorDTO> listField) {
        this.timestamp = timestamp;
        this.message = message;
        this.success = success;
        this.details = details;
        this.listField = listField;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public List<FieldErrorDTO> getListField() {
        return listField;
    }

    public void setListField(List<FieldErrorDTO> listField) {
        this.listField = listField;
    }
}
