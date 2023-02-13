package com.example.movidebapi.models;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@MappedSuperclass
public class BasicEntity {

    @Getter @Setter
    @Column(nullable = false,updatable = false)
    private LocalDate createdAt;
    @Getter @Setter
    private LocalDate updatedAt;
    @Getter @Setter
    @Column(nullable = false)
    private Boolean active;

    @PrePersist
    public void actionPrePersist(){
        setCreatedAt(LocalDate.now());
        setUpdatedAt(LocalDate.now());
    }

    @PreUpdate
    public void actionPreUpdate(){
        setUpdatedAt(LocalDate.now());
    }

    }
