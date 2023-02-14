package com.example.movidebapi.models;

import lombok.Data;


public record WhereMovie(
        Long id,
        String title,
        String[] tags,
        Integer year,
        String actors,
        Long actorId,

        Boolean detail,
        Boolean fanart
) {

}
