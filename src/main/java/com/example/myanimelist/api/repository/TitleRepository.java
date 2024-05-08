package com.example.myanimelist.api.repository;

import com.example.myanimelist.api.entities.Title;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TitleRepository extends JpaRepository<Title, Long> {
}
