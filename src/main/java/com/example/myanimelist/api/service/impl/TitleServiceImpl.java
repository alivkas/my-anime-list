package com.example.myanimelist.api.service.impl;

import com.example.myanimelist.api.dto.TitleDto;
import com.example.myanimelist.api.entities.Genre;
import com.example.myanimelist.api.entities.Studio;
import com.example.myanimelist.api.entities.Title;
import com.example.myanimelist.api.repository.GenreRepository;
import com.example.myanimelist.api.repository.StudioRepository;
import com.example.myanimelist.api.repository.TitleRepository;
import com.example.myanimelist.api.service.TitleService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Transactional
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TitleServiceImpl implements TitleService {

    TitleRepository titleRepository;
    StudioRepository studioRepository;
    GenreRepository genreRepository;

    @Override
    public void addTitle(TitleDto titleDto) {
        Title title = new Title();
        Studio studio = new Studio();

        title.setName(titleDto.name());
        title.setDate(titleDto.date());

        if (!studioRepository.existsByName(titleDto.studio())) {
            studio.setName(titleDto.studio());
            title.setStudio(studio);
            studio.getTitles().add(title);

            studioRepository.save(studio);
        } else {
            title.setStudio(studioRepository.findByName(titleDto.studio()));
            studio.getTitles().add(title);
        }

        for (String genreName : titleDto.genres()) {
            Genre genre = new Genre();

            if (!genreRepository.existsByName(genreName)) {
                genre.setName(genreName);
                genre.getTitles().add(title);
                title.getGenres().add(genre);
                genreRepository.save(genre);
            } else {
                title.getGenres().add(genreRepository.findByName(genreName));
                genre.getTitles().add(title);
            }
        }

        titleRepository.save(title);
    }
}