package dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception;

public class DuplicateRecordException extends ApiException {

    public DuplicateRecordException(String message) {
        super(message);
    }
    
}
