package com.example.projectBackend.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateUser() {

        Map<String, String> error = new HashMap<>();
        error.put("error", "USER_ALREADY_EXISTS");

        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(error);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errors);
    }
    @ExceptionHandler(TransactionSystemException.class)
    public ResponseEntity<Map<String, String>> handleTransactionException(
            TransactionSystemException ex) {

        Throwable cause = ex.getRootCause();

        if (cause instanceof ConstraintViolationException violationEx) {

            Map<String, String> errors = new HashMap<>();

            violationEx.getConstraintViolations().forEach(v ->
                    errors.put(
                        v.getPropertyPath().toString(),
                        v.getMessage()
                    )
            );

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errors);
        }

        // fallback
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of("error", "Transaction failed"));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleBusinessRuleViolation(
                    IllegalArgumentException ex) {

            return ResponseEntity
                            .status(HttpStatus.CONFLICT)
                            .body(Map.of("error", ex.getMessage()));
    }
    
}
