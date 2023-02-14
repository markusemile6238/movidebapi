package com.example.movidebapi.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HttpException extends RuntimeException{

    private ExceptionDto exception;

    public HttpException(HttpStatus status, String message) {
        super(message);
        this.exception = ExceptionDto.builder()
                .status(status.value())
                .message(message)
                .build();
    }
}
