package com.example.movidebapi.services;

import com.example.movidebapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieServiceimpl implements MovieService{

    @Autowired
    private final MovieRepository repository;

    public MovieServiceimpl(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public List findAll(Specification specification) {
        return repository.findAll(specification);
    }

    @Override
    public List findAlls() {
        return repository.findAll();
    }
}
