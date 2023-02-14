package com.example.movidebapi.services.actor;

import com.example.movidebapi.Exceptions.NoFoundException;
import com.example.movidebapi.Exceptions.ServerError;
import com.example.movidebapi.models.Actor;
import com.example.movidebapi.repository.ActorRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActorServiceImpl implements ActorService{
    private final ActorRepository repository;

    public ActorServiceImpl(ActorRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Actor> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Actor> getAllSpec(Specification spec) {
        return repository.findAll(spec);
    }

    @Override
    public Actor add(Actor obj) {
        Optional<Actor> opt = this.findByFirstNLastName(obj.getFirstname(), obj.getLastname());
        opt.ifPresent(value->obj.setId(opt.get().getId()));
        return repository.save(obj);
    }

    @Override
    public Optional<Actor> findByFirstNLastName(String f, String l) {
        Optional<Actor> actor = repository.findActorByFirstnameIsAndLastnameIsAndActiveIsTrue(StringUtils.capitalize(f),StringUtils.capitalize(l));
        if(actor.isPresent()) {
            return actor;
        }else{
            return  repository.findActorByFirstnameIsAndLastnameIsAndActiveIsTrue(StringUtils.capitalize(l),StringUtils.capitalize(f));
        }
    }

    @Override
    public void remove(Actor obj) {
        repository.delete(obj);
    }

    @Override
    public Optional<Actor> getFirstById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Actor findByFirstNLastName(String name) {
        String[] n = name.split("\\+");
        try {
            Optional<Actor> act = this.findByFirstNLastName(n[0], n[1]);
            act.orElseThrow(() -> new NoFoundException("This Actor doesn't exist !"));
            return act.get();
        }catch(Exception e){
            throw new ServerError("Error request ");
        }
    }
}
