package dev.waamir.hotelaluraapi.infrastructure.rest.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import dev.waamir.hotelaluraapi.adapter.dto.exception.DataValidationError;
import dev.waamir.hotelaluraapi.adapter.dto.resource.MessageResponse;

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

    @ExceptionHandler(UserDisabledException.class)
    public ResponseEntity<MessageResponse> handleUserDiasbled() {
        return ResponseEntity
            .status(HttpStatusCode.valueOf(403))
            .body(
                MessageResponse.builder()
                    .message("User is disabled. Please verify the account or contact the admin.")
                    .build()
            );
    }

    @ExceptionHandler(WrongCredentialsException.class)
    public ResponseEntity<MessageResponse> handleBadCreditials() {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(
                MessageResponse.builder()
                    .message("Invalid authentication credentials. Please verify the data and try again.")
                    .build()
            );
    }

    @ExceptionHandler(UserNotFoundException.class) 
    public ResponseEntity<MessageResponse> handleUserNotFound() {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                MessageResponse.builder()
                    .message("User not found")
                    .build()
            );
    }

    @ExceptionHandler(DuplicateRecordException.class)
    public ResponseEntity<MessageResponse> handleDuplicateRecord(DuplicateRecordException e) {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(
                MessageResponse.builder()
                    .message(e.getMessage())
                    .build()
            );
    }

    @ExceptionHandler(UserAlreadyEnabledException.class)
    public ResponseEntity<MessageResponse> handleUserAlreadyEnabled() {
        return ResponseEntity
            .status(HttpStatus.ALREADY_REPORTED)
            .body(
                MessageResponse.builder()
                    .message("Account has already been enabled")   
                    .build()
            );
    }

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<MessageResponse> handleRoomNotFound() {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                MessageResponse.builder()
                    .message("Room not found.")
                    .build()
            );
    }

    @ExceptionHandler(RoomTypeNotFoundException.class)
    public ResponseEntity<MessageResponse> handleRoomTypeNotFound() {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                MessageResponse.builder()
                    .message("Room type not found.")
                    .build()
            );
    }

    @ExceptionHandler(ApiNotFoundException.class)
    public ResponseEntity<MessageResponse> handleNotFound(ApiNotFoundException e) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                MessageResponse.builder()
                    .message(e.getMessage())
                    .build()
            );
    }
}
