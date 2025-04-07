package com.todoBackend.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle validation errors (e.g., missing title or description)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        Map<String, Object> body = new HashMap<>();
        body.put("code", "VALIDATION_ERROR");
        body.put("message", "Bad Request");
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // Handle duplicate task errors (e.g., duplicate title)
    @ExceptionHandler(DuplicateTaskException.class)
    public ResponseEntity<Object> handleDuplicateTask(DuplicateTaskException ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("code", "DUPLICATE_TASK");
        body.put("message", "Bad Request");
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("errors", Map.of("title", ex.getMessage()));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // Handle general DataIntegrityViolationException (e.g., database constraint violations)
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolation(DataIntegrityViolationException ex, WebRequest request) {
        String message = ex.getRootCause() != null ? ex.getRootCause().getMessage() : ex.getMessage();

        Map<String, Object> body = new HashMap<>();
        body.put("code", "DUPLICATE_TASK");
        body.put("message", "Bad Request");
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("errors", Map.of("title", message));

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    // Handle general unexpected exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleGlobalException(Exception ex, WebRequest request) {
        Map<String, Object> body = new HashMap<>();
        body.put("code", "INTERNAL_SERVER_ERROR");
        body.put("message", "Internal Server Error");
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("errors", Map.of("error", ex.getMessage()));

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
