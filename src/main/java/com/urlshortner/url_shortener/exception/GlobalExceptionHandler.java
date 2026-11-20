package com.urlshortner.url_shortener.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ShortUrlNotFoundException.class)
    public ResponseEntity<String> handleShortUrlNotFound(
            ShortUrlNotFoundException exception) {

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(ShortUrlInactiveException.class)
    public ResponseEntity<String> handleShortUrlInactive(
            ShortUrlInactiveException exception) {

        return ResponseEntity
                .status(HttpStatus.GONE)
                .body(exception.getMessage());
    }
}