package com.example.movidebapi.repository;

import com.example.movidebapi.models.Movie;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long>{

    List<Movie> findAll(Specification specification);
    List<Movie> findAll();

    Optional<Movie> getMovieByActiveIsTrueAndTitleIsAndYearIs(String title, Integer year);
}
