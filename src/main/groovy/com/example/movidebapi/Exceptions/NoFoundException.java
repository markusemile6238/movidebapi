package com.example.movidebapi.Exceptions;

import org.springframework.http.HttpStatus;

public class NoFoundException extends HttpException{
    public NoFoundException(String message) {
        super(HttpStatus.NOT_FOUND,message);

    }
}
