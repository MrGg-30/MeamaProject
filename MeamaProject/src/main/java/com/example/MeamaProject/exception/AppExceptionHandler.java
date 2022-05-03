package com.example.MeamaProject.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = BusinessException.class)
    protected ResponseEntity<Object> handleBusinessException(BusinessException ex, WebRequest request) {
        String body = new ObjectMapper()
                .createObjectNode()
                .put("errorCode", ex.getMessage())
                .toString();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return handleExceptionInternal(ex, body, headers, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = SecurityViolationException.class)
    public ResponseEntity<Object> handleSecurityViolation(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, null, HttpHeaders.EMPTY, HttpStatus.FORBIDDEN, request);
    }
}
