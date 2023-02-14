package com.example.movidebapi.models.Tdos;

import com.example.movidebapi.models.Actor;
import com.example.movidebapi.models.Gender;
import com.example.movidebapi.models.Movie;
import lombok.*;

import java.time.LocalDate;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ActorDto {

    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String firstname;
    @Getter @Setter
    private String lastname;
    @Getter @Setter
    private Gender gender;
    @Getter @Setter
    private LocalDate birthday;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ActorDto actorDto)) return false;
        return getId().equals(actorDto.getId()) && getFirstname().equals(actorDto.getFirstname()) && getLastname().equals(actorDto.getLastname()) && getGender() == actorDto.getGender() && getBirthday().equals(actorDto.getBirthday());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstname(), getLastname(), getGender(), getBirthday());
    }

    @Override
    public String toString() {
        return "ActorDto{" +
                "id:" + id +
                ", firstname:'" + firstname + '\'' +
                ", lastname:'" + lastname + '\'' +
                ", gender:" + gender +
                ", birthday:" + birthday +
                '}';
    }

    public static ActorDto actorToDto(Actor actor){
        return ActorDto.builder()
                .id(actor.getId())
                .firstname(actor.getFirstname())
                .lastname(actor.getLastname())
                .gender(actor.getGender())
                .birthday(actor.getBirthday())
                .build();
    }

    public static Actor dtoToActor(ActorDto aDto){
        return Actor.builder()
                .id(aDto.getId())
                .firstname(aDto.getFirstname())
                .lastname(aDto.getLastname())
                .gender(aDto.getGender())
                .birthday(aDto.getBirthday())
                .build();
    }
}
