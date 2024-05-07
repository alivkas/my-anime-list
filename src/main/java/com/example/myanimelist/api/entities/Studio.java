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
@Table(name = "studios")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Studio extends BasicEntity {

    @Column(name = "name")
    String name;

    @OneToMany(mappedBy = "studio", cascade = CascadeType.ALL)
    Set<Title> titles = new HashSet<>();
}
