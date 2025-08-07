package com.hrd.jpa_hibernate_homework.exception;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import com.hrd.jpa_hibernate_homework.model.base.BaseController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@RestControllerAdvice
public class GlobalException extends BaseController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> methodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> error = new HashMap<>();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return problemDetailResponseEntity(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ProblemDetail> handlerMethodValidationException(
            HandlerMethodValidationException e) {
        Map<String, String> errors = new HashMap<>();
        // Loop through each invalid parameter validation result
        e.getParameterValidationResults().forEach(parameterError -> {
            String paramName = parameterError.getMethodParameter().getParameterName();

            // Loop through each validation error message for this parameter
            for (var messageError : parameterError.getResolvableErrors()) {
                errors.put(paramName, messageError.getDefaultMessage()); // Store error message
            }
        });

        // Create structured ProblemDetail response
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.BAD_REQUEST);
        problemDetail.setTitle("Method Parameter Validation Failed");
        problemDetail.setProperties(Map.of("timestamp", LocalDateTime.now(), "errors", errors));
        return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(NotFoundException e) {
        return problemDetailResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
    }

    private static final Logger logger = LoggerFactory.getLogger(GlobalException.class);

    // Handle all unhandled exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAllUnhandledExceptions(Exception e) {
        logger.error("Unhandled exception occurred", e);
        StackTraceElement[] stack = e.getStackTrace();
        String errorMsg = "";
        if (stack.length > 0) {
            StackTraceElement origin = stack[0];
            errorMsg = e.getClass().getSimpleName() + " at " + origin.getClassName() + "."
                    + origin.getMethodName() + "(" + origin.getFileName() + ":"
                    + origin.getLineNumber() + ")";
        } else {
            errorMsg = e.getClass().getSimpleName();
        }

        return problemDetailResponseEntity(errorMsg, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
