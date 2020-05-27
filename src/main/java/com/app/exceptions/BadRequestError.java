package com.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestError extends Exception {
    private int errorCode;

    public BadRequestError(int errorCode, String errorMessage){
        super(errorMessage);
        this.errorCode = errorCode;
    }

    public int getExceptionCode() {
        return this.errorCode;
    }
}
