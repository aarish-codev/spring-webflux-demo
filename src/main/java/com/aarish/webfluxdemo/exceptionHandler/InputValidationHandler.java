package com.aarish.webfluxdemo.exceptionHandler;

import com.aarish.webfluxdemo.dto.InvalidRequestResponse;
import com.aarish.webfluxdemo.exception.InvalidRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class InputValidationHandler {

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<InvalidRequestResponse> handleException(InvalidRequestException exception){
        InvalidRequestResponse response = new InvalidRequestResponse();
        response.setErrorCode(exception.getErrorCode());
        response.setMessage(exception.getMessage());
        response.setInput(exception.getInput());
        return ResponseEntity.internalServerError().body(response);
    }
}
