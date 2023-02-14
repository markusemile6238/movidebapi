package com.example.movidebapi.Exceptions;

import org.springframework.http.HttpStatus;

public class ServerError extends HttpException{

    public ServerError(String message) {
        super(HttpStatus.INTERNAL_SERVER_ERROR,message);
    }
}
