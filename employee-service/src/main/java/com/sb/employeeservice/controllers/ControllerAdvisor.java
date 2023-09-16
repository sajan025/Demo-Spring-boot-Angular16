package com.sb.employeeservice.controllers;

import com.sb.employeeservice.exceptions.UserCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
@RestController
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserCustomException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserCustomException exception, WebRequest request){
        Map <String,Object> body=new LinkedHashMap<>();
        body.put("timestamp", LocalDateTime.now());
        body.put("message", "User not found");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

}
