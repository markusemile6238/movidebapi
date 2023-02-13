package com.example.movidebapi.models;

import lombok.Data;


public record WhereMovie(
        Long id,
        String title,
        Integer year,
        String Actor
) {

}
