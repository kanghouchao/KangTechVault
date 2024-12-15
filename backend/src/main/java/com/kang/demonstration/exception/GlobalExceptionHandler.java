package com.kang.demonstration.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author kanghouchao
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        List<ValidationError> validationErrors = fieldErrors.stream()
            .map(fieldError -> new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()))
            .collect(Collectors.toList());

        return new ResponseEntity<>(new ErrorMessage(null, validationErrors), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> emailAlreadyRegistered(ServiceException e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(e.getMessage(), null), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGenericException(Exception e) {
        log.error(e.getMessage(), e);
        return new ResponseEntity<>(new ErrorMessage(null, null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    record ErrorMessage(String message,
                        List<ValidationError> validationErrors) {

    }

    record ValidationError(String field, String message) {

    }

}

