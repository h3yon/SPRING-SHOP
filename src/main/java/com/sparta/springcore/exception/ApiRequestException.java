package com.sparta.springcore.exception;

/**
 * API에 대한 ExceptionHandler
 */
public class ApiRequestException extends IllegalArgumentException {
    public ApiRequestException(String message) {
        super(message);
    }

    public ApiRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}