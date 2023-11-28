package dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception;

public class UserAlreadyEnabledException extends ApiException {

    public UserAlreadyEnabledException(String message) {
        super(message);
    }
    
}
