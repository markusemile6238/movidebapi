package com.example.movidebapi.controller;

import com.example.movidebapi.models.Movie;
import com.example.movidebapi.models.WhereMovie;
import com.example.movidebapi.repository.MovieRepository;
import com.example.movidebapi.services.MovieService;
import groovyjarjarpicocli.CommandLine;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/movie")
public class MovieController {

    @Autowired
    private  MovieService serviceMovie;
    @Autowired
    private MovieRepository movieRepository;

    public MovieController(MovieService serviceMovie) {
        this.serviceMovie = serviceMovie;
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Movie> actionGetAll(WhereMovie whereMovie) throws IllegalAccessException, InvocationTargetException {

        List<WhereMovie> filter = new ArrayList<>();
        List<Specification<Movie>> specs = new ArrayList<>();

        RecordComponent[] allfilters = whereMovie.getClass().getRecordComponents();

        for (int i = 0; i < allfilters.length; i++) {
            if(allfilters[i].getAccessor().invoke(whereMovie) != null){
                System.out.println(allfilters[i].getAccessor());
                int finalI = i;

                Object invoke = allfilters[finalI].getAccessor().invoke(whereMovie);
                String w = (allfilters[finalI].getAccessor().invoke(whereMovie)).toString();

                specs.add((m, cq, cb) -> cb.like(m.get(allfilters[finalI].getAccessor().getName()),"%"+w+"%"));
            }
        }

        return movieRepository.findAll(Specification.anyOf(specs));

//            Specification<Movie> spec = null;
//            if (whereMovie.year() != null) {
//                spec = new Specification<Movie>() {
//                    @Override
//                    public Predicate toPredicate(Root<Movie> m, CriteriaQuery<?> cq, CriteriaBuilder cb) {
//                        return cb.equal(m.get("year"), whereMovie.year());
//                    }
//                };
//                return serviceMovie.findAll(spec);
//            }

        //return serviceMovie.findAlls();
    }
}
