package com.kang.demonstration.config;

import com.kang.demonstration.auth.exception.EmailAlreadyRegisteredException;
import jakarta.mail.MessagingException;
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

    @ExceptionHandler(EmailAlreadyRegisteredException.class)
    public ResponseEntity<?> emailAlreadyRegistered(EmailAlreadyRegisteredException e) {
        return new ResponseEntity<>(new ErrorMessage(e.getMessage(), null), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MessagingException.class)
    public ResponseEntity<?> handleGenericException(MessagingException e) {
        return new ResponseEntity<>(new ErrorMessage(e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    record ErrorMessage(String message,
                        List<ValidationError> validationErrors) {

    }

    record ValidationError(String field, String message) {

    }

}

