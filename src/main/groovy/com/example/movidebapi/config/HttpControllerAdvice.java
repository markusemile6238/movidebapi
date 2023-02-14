package com.example.movidebapi.config;

import com.example.movidebapi.Exceptions.ExceptionDto;
import com.example.movidebapi.Exceptions.HttpException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HttpControllerAdvice  {

    @ExceptionHandler(value= HttpException.class)
    public ResponseEntity<ExceptionDto> httpExceptionHandler(HttpException exception){
        return new ResponseEntity<>(exception.getException(), HttpStatus.valueOf(exception.getException().getStatus()));
    }
}
