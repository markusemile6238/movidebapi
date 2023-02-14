package com.example.movidebapi.controller;

import com.example.movidebapi.Exceptions.NoFoundException;
import com.example.movidebapi.Exceptions.ServerError;
import com.example.movidebapi.models.Actor;
import com.example.movidebapi.models.Movie;
import com.example.movidebapi.models.Tag;
import com.example.movidebapi.models.Tdos.MovieDto;
import com.example.movidebapi.models.WhereMovie;
import com.example.movidebapi.services.actor.ActorService;
import com.example.movidebapi.services.movie.MovieService;
import com.example.movidebapi.services.tag.TagService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.RecordComponent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path="/movie")
public class MovieController {

    private  final MovieService serviceMovie;
    private final TagService serviceTag;
    private final ActorService serviceActor;

    public MovieController(MovieService serviceMovie, TagService serviceTag, ActorService serviceActor) {
        this.serviceMovie = serviceMovie;
        this.serviceTag = serviceTag;
        this.serviceActor = serviceActor;

    }

    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<?> actionGetAll(WhereMovie whereMovie) throws IllegalAccessException, InvocationTargetException {

        List<Specification<Movie>> specs = new ArrayList<>();
        boolean haveHttpMeta = false;

        RecordComponent[] allfilters = whereMovie.getClass().getRecordComponents();
        for (RecordComponent allfilter : allfilters)
            if (allfilter.getAccessor().invoke(whereMovie) != null) {
                haveHttpMeta = true;
            }
        if(haveHttpMeta){
            if(whereMovie.year()!=null){
                    specs.add((m,cq,cb)->cb.equal(m.get("year"),whereMovie.year()));
            }
            if(whereMovie.tags() != null){
                //check si integer ou autre
                List<Tag> tagsToSearch = serviceTag.getAllbyNameIn(Arrays.asList(whereMovie.tags()));
                tagsToSearch.forEach(tag->specs.add((m,cq,cb)->cb.isMember(tag,m.get("tags"))));
            }
            if(whereMovie.actors() != null){
                Actor actor = serviceActor.findByFirstNLastName(whereMovie.actors());
                specs.add((m,cq,cb)->cb.isMember(actor,m.get("actors")));
            }

            List<MovieDto> response = serviceMovie.getAllSpec(Specification.allOf(specs)).stream().map(MovieDto::movieToDto).collect(Collectors.toList());

            if(response.size()>0){
                return ResponseEntity.ok(response);
            }else{
                return new ResponseEntity<>("No Data",HttpStatus.NOT_FOUND);
            }
        }else {
            return ResponseEntity.ok(serviceMovie.getAll().stream().map(MovieDto::movieToDto).collect(Collectors.toList()));
        }

    }
    @GetMapping(path="/{id:[0-9]}")
    public ResponseEntity<Movie> actionGetOne(@PathVariable("id") Long id){
        Movie movie = serviceMovie
                .getFirstById(id)
                .orElseThrow(()->new NoFoundException("This id movie doesn't exist !!"));
        return ResponseEntity.ok(movie);
    }

    @PostMapping(path="/add")
    public ResponseEntity<Movie> actionAdd(@RequestBody Movie movie){
      // on sauve les tags
      movie.getTags().forEach(serviceTag::add);
      // on sauve les actors
      movie.getActors().forEach(serviceActor::add);
      Movie _movie = serviceMovie.add(movie);
      return new ResponseEntity<>(_movie, HttpStatus.CREATED);
    }

    @DeleteMapping(path="/del/{id:[0-9]+}")
    public ResponseEntity<String> actionDel(@PathVariable("id") Long id){
        Optional<Movie> opt = serviceMovie.getFirstById(id);
        opt.orElseThrow(()->new NoFoundException("Not movie with this id"));
        serviceMovie.remove(opt.get());
        return ResponseEntity.ok("Movie was deleted with successfully!");
    }

    @PutMapping(path="/update/{id:[0-9]+}")
    public ResponseEntity<String> actionUpdate(@PathVariable("id") Long id){
        Movie movie = serviceMovie
                .getFirstById(id)
                .orElseThrow(()->new NoFoundException("This Movie id doesn't exist"));
        try{
            serviceMovie.add(movie);
            return ResponseEntity.ok("Movie was successfully updated");
        }catch(Exception e){
            throw new ServerError("Movie was not successfully updated !!");
        }

    }

}
