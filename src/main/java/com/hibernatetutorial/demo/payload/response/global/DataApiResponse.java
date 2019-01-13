package com.hibernatetutorial.demo.payload.response.global;

import java.util.Date;

public class DataApiResponse {

    private Long timestamp;

    private boolean success;

    private Object data;

    public DataApiResponse(boolean success, Object data) {
        this.success = success;
        this.data = data;
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
}
