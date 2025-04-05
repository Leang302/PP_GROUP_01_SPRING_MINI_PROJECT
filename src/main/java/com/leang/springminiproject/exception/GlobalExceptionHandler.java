package com.leang.springminiproject.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import javax.naming.AuthenticationException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, e.getMessage());
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    @ExceptionHandler({NotVerifiedException.class, InvalidException.class})
    public ProblemDetail handleNotVerifiedException(Exception e) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, e.getMessage());
        problemDetail.setTitle("Bad Request");
        problemDetail.setProperty("timestamp", Instant.now());
        return problemDetail;
    }

    //     Handle validation errors
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(MethodArgumentNotValidException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Validation Failed");
        problemDetail.setProperty("timestamp", LocalDateTime.now());

        // Collect field validation errors
        Map<String, String> errors = new HashMap<>();
        int blankFieldErrors = 0;

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());

            // Check if the error is specifically "must not be blank"
            if ("must not be blank".equals(fieldError.getDefaultMessage())) {
                blankFieldErrors++;
            }
        }

        // Add errors to ProblemDetail as extra properties
        problemDetail.setProperty("errors", errors);

        return problemDetail;
    }


    @ExceptionHandler(HandlerMethodValidationException.class)
    public ProblemDetail handleMethodValidationException(HandlerMethodValidationException e) {
        Map<String, String> errors = new HashMap<>();

        // Loop through each invalid parameter validation result
        e.getParameterValidationResults().forEach(parameterError -> {
            String paramName = parameterError.getMethodParameter().getParameterName(); // Get parameter name

            // Loop through each validation error message for this parameter
            for (var messageError : parameterError.getResolvableErrors()) {
                errors.put(paramName, messageError.getDefaultMessage()); // Store error message
            }
        });

        // Create structured ProblemDetail response
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Method Parameter Validation Failed");
        problemDetail.setProperties(Map.of("timestamp", LocalDateTime.now(), "errors", errors // Attach validation errors
        ));

        return problemDetail;
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ProblemDetail handleDataIntegrityViolation(DataIntegrityViolationException ex) {
        Throwable rootCause = ex.getRootCause();
        String detailMessage = "Data integrity violation.";
        HttpStatus status = HttpStatus.CONFLICT;
        Map<String, String> fields = new HashMap<>();


        String message = rootCause.getMessage();

        if (message.contains("app_users_email_key")) {
            fields.put("email", "Email has already existed.");
        }
        if (message.contains("app_users_username_key")) {
            fields.put("name", "Name has already been taken.");
        }


        ProblemDetail problem = ProblemDetail.forStatusAndDetail(status, detailMessage);
        problem.setTitle("Duplicate Field");

        problem.setProperty("fields", fields);

        return problem;
    }


}