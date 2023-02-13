package com.example.movidebapi.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString
public class Movie extends BasicEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column(nullable = false)
    @Getter @Setter
    private String  title;

    @Column(nullable = false)
    @Getter @Setter
    private Integer year;

    @Column(nullable = false)
    @Getter @Setter
    private Integer duration;


    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name="Movie_tag",
            joinColumns =@JoinColumn(name="id_movie",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="id_tag",referencedColumnName = "id")
            )
    private List<Tag> tags = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Movie movie)) return false;
        return Objects.equals(getId(), movie.getId()) && Objects.equals(getTitle(), movie.getTitle()) && Objects.equals(getYear(), movie.getYear()) && Objects.equals(getDuration(), movie.getDuration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getYear(), getDuration());
    }
}
