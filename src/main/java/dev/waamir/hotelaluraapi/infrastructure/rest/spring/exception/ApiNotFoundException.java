package dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception;

public class ApiNotFoundException extends ApiException {

    public ApiNotFoundException(String message) {
        super(message);
    }
    
}
