package dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception;

import org.springframework.http.HttpStatusCode;

public class GenericException extends ApiException{

    private HttpStatusCode status;
    private String message;

    public GenericException(String message, HttpStatusCode status, String superMessage) {
        super(superMessage);
        this.status = status;
        this.message = message;
    }
    
    public HttpStatusCode getStatus() {
        return this.status;
    }

    public String getMyMessage() {
        return this.message;
    }
}
