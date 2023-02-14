package com.example.movidebapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Actor extends BasicEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;
    @Getter @Setter
    @Column(nullable = false,length = 50)
    private String firstname;
    @Getter @Setter
    @Column(nullable = false,length = 50)
    private String lastname;
    @Getter @Setter
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Getter @Setter
    private LocalDate birthday;

    @ManyToMany(mappedBy = "actors")
    @JsonIgnore
    private List<Movie> movies;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Actor actor)) return false;
        return Objects.equals(getId(), actor.getId()) && Objects.equals(getFirstname(), actor.getFirstname()) && Objects.equals(getLastname(), actor.getLastname()) && getGender() == actor.getGender() && Objects.equals(getBirthday(), actor.getBirthday());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstname(), getLastname(), getGender(), getBirthday());
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                '}';
    }
}
