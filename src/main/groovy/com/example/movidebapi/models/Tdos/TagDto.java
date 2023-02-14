package com.example.movidebapi.models.Tdos;

import com.example.movidebapi.models.Movie;
import com.example.movidebapi.models.Tag;
import lombok.*;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TagDto {

    @Getter @Setter
    private Long id;
    @Getter @Setter
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TagDto tagDto)) return false;
        return getId().equals(tagDto.getId()) && getName().equals(tagDto.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "TagDto{" +
                "id:" + id +
                ", name:'" + name + '\'' +
                '}';
    }

    public static TagDto tagToDto(Tag tag){
        return TagDto.builder()
                .id(tag.getId())
                .name(tag.getName())
                .build();
    }

    public static Tag dtoToTag(TagDto mDto){
        return Tag.builder()
                .id(mDto.getId())
                .name(mDto.getName())
                .build();
    }
}
