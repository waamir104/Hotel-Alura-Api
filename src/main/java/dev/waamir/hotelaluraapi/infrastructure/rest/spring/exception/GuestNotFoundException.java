package dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception;

public class GuestNotFoundException extends ApiException {

    public GuestNotFoundException(String message) {
        super(message);
    }
    
}
