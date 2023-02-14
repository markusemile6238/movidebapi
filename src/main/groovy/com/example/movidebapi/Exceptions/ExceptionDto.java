package com.example.movidebapi.Exceptions;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class ExceptionDto {

    private String message;

    private int status;

    private Map<String,String> error;

}
