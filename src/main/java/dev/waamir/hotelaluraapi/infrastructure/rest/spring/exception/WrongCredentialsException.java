package dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception;

public class WrongCredentialsException extends ApiException {

    public WrongCredentialsException(String message) {
        super(message);
    }
    
}
