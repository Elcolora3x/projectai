package com.elcolora2x.dev.projectai.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EventFullCapacityAccomplishedException.class)
    public ResponseEntity<ErrorResponse> handleEventFull(EventFullCapacityAccomplishedException ex, HttpServletRequest request) {
        var status = HttpStatus.BAD_REQUEST;
        var body = new ErrorResponse(status.value(), status.getReasonPhrase(), ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(status).body(body);
    }

    // Manejo de validaciones de Bean Validation (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex, HttpServletRequest request) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> 
            errors.put(error.getField(), error.getDefaultMessage())
        );

        var status = HttpStatus.UNPROCESSABLE_CONTENT;
        var body = new ErrorResponse(
            Instant.now(),
            status.value(),
            status.getReasonPhrase(),
            "Error de validación en los campos enviados",
            request.getRequestURI(),
            errors
        );
        return ResponseEntity.status(status).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAll(Exception ex, HttpServletRequest request) {
        var status = HttpStatus.INTERNAL_SERVER_ERROR;
        var body = new ErrorResponse(status.value(), status.getReasonPhrase(), "Error interno inesperado", request.getRequestURI());
        return ResponseEntity.status(status).body(body);
    }
}