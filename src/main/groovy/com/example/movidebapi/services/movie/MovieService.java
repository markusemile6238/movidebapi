package com.example.movidebapi.services.movie;

import com.example.movidebapi.models.Movie;
import com.example.movidebapi.services.CrudService;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;


public interface MovieService extends CrudService<Movie,Long> {


 Optional<Movie> findByTitleNYear(String title, Integer year);

}
