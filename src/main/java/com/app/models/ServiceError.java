package com.app.models;

import org.springframework.http.HttpStatus;

public class ServiceError {
    private int errorCode;
    private String message;
    private HttpStatus status;

    public ServiceError(int errorCode, HttpStatus status, String message) {
        this.errorCode = errorCode;
        this.status = status;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
