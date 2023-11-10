package dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message)  {
        super(message);
    }
}
