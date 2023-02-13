package com.example.movidebapi.models;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@ToString

public class Fanart{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Getter @Setter
    private FanartType fType;

    @Column(nullable = false)
    @Getter @Setter
    private String path;

}