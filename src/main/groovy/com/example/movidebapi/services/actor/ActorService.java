package com.example.movidebapi.services.actor;

import com.example.movidebapi.models.Actor;
import com.example.movidebapi.services.CrudService;

import java.util.List;
import java.util.Optional;

public interface ActorService extends CrudService<Actor,Long> {

    Optional<Actor> findByFirstNLastName(String f,String l);
    Actor findByFirstNLastName(String name);

}
