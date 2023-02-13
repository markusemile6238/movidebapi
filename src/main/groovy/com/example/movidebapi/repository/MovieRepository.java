package com.example.movidebapi.repository;

import com.example.movidebapi.models.Movie;
import jakarta.persistence.EntityManager;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long>{


    List<Movie> findAll(Specification specification);
    List<Movie> findAll();
}
