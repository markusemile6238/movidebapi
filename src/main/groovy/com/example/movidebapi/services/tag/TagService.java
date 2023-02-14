package com.example.movidebapi.services.tag;

import com.example.movidebapi.models.Tag;
import com.example.movidebapi.services.CrudService;

import java.util.List;


public interface TagService extends CrudService<Tag,Long> {

    List<Tag> getAllbyNameIn(List<String> listeDeTag);

}
