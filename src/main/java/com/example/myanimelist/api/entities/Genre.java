package com.example.myanimelist.api.entities;

import com.example.myanimelist.api.entities.base.BasicEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "genres")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Genre extends BasicEntity {

    @Column(name = "name")
    String name;

    @ManyToMany(mappedBy = "genres")
    Set<Title> titles = new HashSet<>();
}
