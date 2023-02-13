package com.example.movidebapi.models;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Actor extends BasicEntity{
    private Long id;
    private String firstname;
    private String lastname;
    private Gender gender;
    private LocalDate birthday;
    
}
