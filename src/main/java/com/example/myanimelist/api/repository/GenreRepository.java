package com.example.myanimelist.api.repository;

import com.example.myanimelist.api.entities.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findByName(String name);
    boolean existsByName(String name);
}
