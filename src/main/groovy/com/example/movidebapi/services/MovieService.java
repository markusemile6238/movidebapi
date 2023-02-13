package com.example.movidebapi.services;

import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface MovieService<Movie,Long> {


    List<Movie> findAll(Specification specification);
    List<Movie> findAlls();


}
