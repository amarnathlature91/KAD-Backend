package com.kad.exception;

import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAllExceptions(Exception ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<String> handleCustomException(ExpiredJwtException ex) {
        return new ResponseEntity<>("Session Expired Login Again: " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
}