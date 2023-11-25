package dev.waamir.hotelaluraapi.adapter.dto.exception;

import org.springframework.validation.FieldError;

public record DataValidationError(String field, String error) {
    public DataValidationError(FieldError error) {
        this(error.getField(), error.getDefaultMessage());
    }
}
