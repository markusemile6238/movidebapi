package com.example.movidebapi.services.movie;

import com.example.movidebapi.models.Movie;
import com.example.movidebapi.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceimpl implements MovieService{


    private final MovieRepository repository;

    public MovieServiceimpl(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Movie> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Movie> getAllSpec(Specification spec) {
        return repository.findAll(spec);
    }

    @Override
    public Movie add(Movie obj) {
        Optional<Movie> opt = repository.getMovieByActiveIsTrueAndTitleIsAndYearIs(obj.getTitle(), obj.getYear());
        opt.ifPresent(value->obj.setId(value.getId()));
        return repository.save(obj);
    }

    @Override
    public Optional<Movie> findByTitleNYear(String title, Integer year) {
        return repository.getMovieByActiveIsTrueAndTitleIsAndYearIs(title,year);
    }

    @Override
    public void remove(Movie obj) {
        repository.delete(obj);
    }

    @Override
    public Optional<Movie> getFirstById(Long id) {
        return repository.findById(id);
    }
}
