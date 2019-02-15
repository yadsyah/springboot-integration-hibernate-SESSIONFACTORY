package com.hibernatetutorial.demo.payload.response.global;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

public class DataApiResponse {

    private Long timestamp;

    private boolean success;

    private Object data;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public DataApiResponse(boolean success, Object data) {
        this.success = success;
        this.data = data;
    }

    public DataApiResponse(boolean success, Object data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }

    public Long getTimestamp() {
        return new Date().getTime();
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
