package com.example.movidebapi.services;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface CrudService<Te,Tk>{


    List<Te> getAll();
    List<Te> getAllSpec(Specification spec);
    Te add(Te obj);
    Optional<Te> getFirstById(Tk id);

    void remove(Te obj);
}
