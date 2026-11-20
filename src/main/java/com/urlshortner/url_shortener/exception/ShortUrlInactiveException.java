package com.urlshortner.url_shortener.exception;

public class ShortUrlInactiveException extends RuntimeException {

    public ShortUrlInactiveException(String message) {
        super(message);
    }
}