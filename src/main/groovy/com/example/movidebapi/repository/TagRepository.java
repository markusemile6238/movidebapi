package com.example.movidebapi.repository;



import com.example.movidebapi.models.Tag;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    List<Tag> findAll(Specification specification);
    List<Tag> findAll();

    Optional<Tag> getFirstByNameIgnoreCase(String name);

    List<Tag> findAllByActiveTrueAndNameIn(Collection<String> names);

}
