package ru.example.securityapp.handlers;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    public RestExceptionHandler() {
        super();
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleMissingElementException(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, ex.getLocalizedMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleBadRequest(Exception ex, WebRequest request) {
        return handleExceptionInternal(ex, "Could not execute statement!", new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
