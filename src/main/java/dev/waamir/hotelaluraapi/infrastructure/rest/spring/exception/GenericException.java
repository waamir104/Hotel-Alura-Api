package dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception;

import org.springframework.http.HttpStatusCode;

public class GenericException extends ApiException{

    private HttpStatusCode status;

    public GenericException(String message, HttpStatusCode status) {
        super(message);
    }
    
    public HttpStatusCode getStatus() {
        return this.status;
    }
}
