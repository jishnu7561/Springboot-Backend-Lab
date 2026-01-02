package com.jishnu.springbootbackendlab.exception;

import com.jishnu.springbootbackendlab.common.ApiError;
import com.jishnu.springbootbackendlab.common.ApiResponse;
import com.jishnu.springbootbackendlab.exception.custom.PaymentProcessingException;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        log.error("Payment Error occurred: {}", exception.getMessage());
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST.value(),
                message,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> exceptionHandler(ConstraintViolationException exception) {
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .httpStatus(HttpStatus.BAD_REQUEST.value())
                .message(exception.getConstraintViolations().iterator().next().getMessage())
                .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PaymentProcessingException.class)
    public ResponseEntity<?> handlePaymentProcessingException(PaymentProcessingException exception) {

        HttpStatus httpStatus = HttpStatus.resolve(exception.getStatus());
        ApiResponse<String> apiResponse = ApiResponse.<String>builder()
                .httpStatus(httpStatus.value())
                .message(exception.getMessage())
                .build();

        return new ResponseEntity<>(apiResponse, httpStatus);
    }
}
