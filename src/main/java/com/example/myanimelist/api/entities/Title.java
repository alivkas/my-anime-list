package com.example.myanimelist.api.entities;

import com.example.myanimelist.api.entities.base.BasicEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "titles")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Title extends BasicEntity {

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "date", nullable = false)
    String date;

    @ManyToOne
    @JoinColumn(name = "studio_id", nullable = false)
    Studio studio;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "titles_genres",
            joinColumns = {
                    @JoinColumn(name = "title_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "genre_id")
            }
    )
    Set<Genre> genres = new HashSet<>();
}
