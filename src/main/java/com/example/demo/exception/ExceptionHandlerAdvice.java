package com.example.demo.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Locale;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException exception, WebRequest request) {

        LOGGER.error("Handle Exception ConstraintViolationException", exception);

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());

        LOGGER.info("Handle Exception ConstraintViolationException Response: {}", problemDetail);
        
        return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handle(HttpMessageConversionException exception) {

        LOGGER.error("Handle Exception HttpMessageConversionException", exception);

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, exception.getMessage());

        LOGGER.info("Handle Exception HttpMessageConversionException Response: {}", problemDetail);

        return new ResponseEntity<>(problemDetail, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        LOGGER.error("Handle Exception MethodArgumentNotValid", ex);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.resolve(status.value()), ex.getMessage());
        LOGGER.info("Handle Exception HttpMessageConversionException Response: {}", problemDetail);
        return super.handleExceptionInternal(ex, problemDetail, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        LOGGER.error("Handle Exception ExceptionInternal", ex);
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.resolve(statusCode.value()), ex.getMessage());
        LOGGER.info("Handle Exception HttpMessageConversionException Response: {}", problemDetail);
        return super.handleExceptionInternal(ex, problemDetail, headers, statusCode, request);
    }


    @ExceptionHandler(Exception.class)
    protected ResponseEntity<Object> handleException(BaseException exception, WebRequest request, Locale locale) {

        LOGGER.error("Handle Exception", exception);

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(exception.status, exception.getMessage());

        LOGGER.info("Handle Exception Response: {}", problemDetail);

        return new ResponseEntity<>(problemDetail, exception.getStatus());
    }

}