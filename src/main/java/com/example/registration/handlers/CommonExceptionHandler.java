package com.example.registration.handlers;

import com.example.registration.model.StandardResponseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class CommonExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = {StandardResponseException.class})
    public ResponseEntity<?> standardResponseExceptionHandler(StandardResponseException exception) {
        return ResponseEntity.status(exception.getHttpCode()).body(exception.getResponse());
    }
}
