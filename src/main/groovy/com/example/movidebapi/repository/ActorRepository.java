package com.example.movidebapi.repository;

import com.example.movidebapi.models.Actor;
import com.example.movidebapi.models.Tag;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ActorRepository extends JpaRepository<Actor,Long> {

    List<Actor> findAll(Specification specification);
    List<Actor> findAll();
    Optional<Actor> findActorByFirstnameIsAndLastnameIsAndActiveIsTrue(String firstname,String lastname);






}
