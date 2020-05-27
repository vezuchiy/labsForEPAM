package com.app.services;

import com.app.exceptions.BadRequestError;
import com.app.exceptions.InternalServiceError;
import com.app.models.ServiceError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorProcessingService {
    @ExceptionHandler({BadRequestError.class, InternalServiceError.class})
    public final ResponseEntity<ServiceError> processError(Exception ex) {

        if (ex instanceof BadRequestError) {
            ServiceError error = new ServiceError(400, HttpStatus.BAD_REQUEST, ex.getMessage());
            return new ResponseEntity<>(error, HttpStatus.OK);
        }

        else if (ex instanceof InternalServiceError) {
            ServiceError error = new ServiceError(500, HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
            return new ResponseEntity<>(error, HttpStatus.OK);
        }

        ServiceError error = new ServiceError(500, HttpStatus.INTERNAL_SERVER_ERROR, "Unknown internal error.");
        return new ResponseEntity<>(error, HttpStatus.OK);
    }
}
