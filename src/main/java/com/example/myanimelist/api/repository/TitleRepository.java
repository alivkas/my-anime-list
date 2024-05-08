package com.example.myanimelist.api.repository;

import com.example.myanimelist.api.entities.Title;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<Title, Long> {

    Title findByName(String name);
    void deleteByName(String name);
    boolean existsByName(String name);
}
