package dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception;

public class UserNotFoundException extends ApiException {

    public UserNotFoundException(String message) {
        super(message);
    }
    
}
