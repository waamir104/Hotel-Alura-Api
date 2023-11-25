package dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.waamir.hotelaluraapi.adapter.dto.exception.DataValidationError;
import dev.waamir.hotelaluraapi.adapter.dto.exception.ErrorMessage;

import java.util.List;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    
    @ExceptionHandler(ApiException.class)
    public void handleApiException(ApiException e) {
        System.out.println(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<DataValidationError>> handle400Error(
        MethodArgumentNotValidException e
    ) {
        List<DataValidationError> errors = e
            .getFieldErrors()
            .stream()
            .map(DataValidationError::new)
            .toList();
        return ResponseEntity.badRequest().body(errors);
    }

    @ExceptionHandler(DisabledException.class)
    public ResponseEntity<ErrorMessage> handleUserDiasbled() {
        return ResponseEntity
            .status(HttpStatusCode.valueOf(403))
            .body(
                ErrorMessage.builder()
                    .message("User is disabled. Please verify the account or contact the admin.")
                    .build()
            );
    }
}
