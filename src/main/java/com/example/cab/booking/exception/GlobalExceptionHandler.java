package com.example.cab.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException notFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(notFoundException.getMessage());
    }

    @ExceptionHandler(NoCabsAvailableException.class)
    public ResponseEntity<String> handleNoCabsAvailableException(NoCabsAvailableException noCabsAvailableException){
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(noCabsAvailableException.getMessage());
    }
}
