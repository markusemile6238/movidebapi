package com.example.movidebapi.models.Tdos;


import com.example.movidebapi.models.Movie;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieDto {
    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String title;
    @Getter @Setter
    private Integer year;
    @Getter @Setter
    private Integer duration;

    @Getter @Setter
    private List<TagDto> tags;

    @Getter @Setter
    private List<ActorDto> actors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MovieDto movieDto)) return false;
        return getId().equals(movieDto.getId()) && getTitle().equals(movieDto.getTitle()) && getYear().equals(movieDto.getYear()) && getDuration().equals(movieDto.getDuration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getYear(), getDuration());
    }

    @Override
    public String toString() {
        return "MovieDto{" +
                "id:" + id +
                ", title:'" + title + '\'' +
                ", year:" + year +
                ", duration:" + duration +
                ", tags:"+ tags +
                ", actors"+actors +
                '}';
    }

    public static MovieDto movieToDto(Movie movie){
        return MovieDto.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .year(movie.getYear())
                .duration(movie.getDuration())
                .tags(movie.getTags().stream().map(TagDto::tagToDto).collect(Collectors.toList()))
                .actors(movie.getActors().stream().map(ActorDto::actorToDto).collect(Collectors.toList()))
                .build();
    }

    public Movie dtoToMovie(MovieDto mDto){
        return Movie.builder()
                .id(mDto.getId())
                .title(mDto.getTitle())
                .year(mDto.getYear())
                .duration((mDto.getDuration()))
                .build();
    }
}
