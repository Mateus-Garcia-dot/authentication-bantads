package com.bantads.authentication.exception;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseStatusExceptionHandler {

    @ExceptionHandler({
            NoSuchElementException.class,
            EmptyResultDataAccessException.class
    })
    public ResponseEntity<String> handleException(Exception e) {
        return ResponseEntity.status(404).body("Auth not found");
    }

    @ExceptionHandler({
            IllegalArgumentException.class
    })
    public ResponseEntity<String> illegalArgumentException(Exception e) {
        return ResponseEntity.status(400).body("Bad arguments");
    }

}
