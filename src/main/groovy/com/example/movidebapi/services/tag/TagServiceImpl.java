package com.example.movidebapi.services.tag;

import com.example.movidebapi.models.Tag;
import com.example.movidebapi.repository.TagRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService{

    private TagRepository repository;

    public TagServiceImpl(TagRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Tag> getAll() {
        return repository.findAll();
    }

    @Override
    public List<Tag> getAllSpec(Specification spec) {
        return repository.findAll(spec);
    }

    @Override
    public Tag add(Tag obj) {
        Optional<Tag> opt = repository.getFirstByNameIgnoreCase(obj.getName());
//        System.out.println(opt.get().getId());
        opt.ifPresent(value->obj.setId(value.getId()));
        return repository.save(obj);
    }

    @Override
    public void remove(Tag obj) {
        repository.delete(obj);
    }

    @Override
    public Optional<Tag> getFirstById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Tag> getAllbyNameIn(List<String> names) {
        return repository.findAllByActiveTrueAndNameIn(names.stream().map(StringUtils::capitalize).collect(Collectors.toList()));
    }
}
