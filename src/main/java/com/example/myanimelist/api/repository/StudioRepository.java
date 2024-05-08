package com.example.myanimelist.api.repository;

import com.example.myanimelist.api.entities.Studio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudioRepository extends JpaRepository<Studio, Long> {

    boolean existsByName(String name);
    Studio findByName(String name);
}
