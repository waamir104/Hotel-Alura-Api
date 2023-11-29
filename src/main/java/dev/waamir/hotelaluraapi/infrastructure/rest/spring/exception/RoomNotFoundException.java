package dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception;

public class RoomNotFoundException extends ApiException {

    public RoomNotFoundException(String message) {
        super(message);
    }
    
}
