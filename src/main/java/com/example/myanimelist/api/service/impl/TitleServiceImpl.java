package com.example.myanimelist.api.service.impl;

import com.example.myanimelist.api.dto.TitleDto;
import com.example.myanimelist.api.entities.Genre;
import com.example.myanimelist.api.entities.Studio;
import com.example.myanimelist.api.entities.Title;
import com.example.myanimelist.api.repository.GenreRepository;
import com.example.myanimelist.api.repository.StudioRepository;
import com.example.myanimelist.api.repository.TitleRepository;
import com.example.myanimelist.api.service.TitleService;
import com.example.myanimelist.error.exception.TitleNotFoundException;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import static com.example.myanimelist.config.common.RabbitNames.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Transactional
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TitleServiceImpl implements TitleService {

    TitleRepository titleRepository;
    StudioRepository studioRepository;
    GenreRepository genreRepository;
    RabbitTemplate rabbitTemplate;

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

        rabbitTemplate.convertAndSend(TITLE_EXCHANGE_NAME, TITLE_CREATE_KEY, titleDto);
    }

    @Override
    public List<TitleDto> getTitles() {
        List<Title> titles = titleRepository.findAll();
        List<TitleDto> titleDtos = new ArrayList<>();

        for (Title title : titles) {
            TitleDto titleDto = new TitleDto(title.getName(),
                    title.getDate(),
                    title.getStudio().getName(),
                    title.getGenres().stream()
                            .map(Genre::getName).
                            toList());
            titleDtos.add(titleDto);
        }

        return titleDtos;
    }

    @Override
    public TitleDto getTitle(String name) {
        if (!titleRepository.existsByName(name)) {
            throw new TitleNotFoundException(name);
        }

        Title title = titleRepository.findByName(name);

        return new TitleDto(title.getName(),
                title.getDate(),
                title.getStudio().getName(),
                title.getGenres().stream()
                        .map(Genre::getName)
                        .toList());
    }

    @Override
    public void deleteTitle(String name) {
        if (!titleRepository.existsByName(name)) {
            throw new TitleNotFoundException(name);
        }
        titleRepository.deleteByName(name);

        rabbitTemplate.convertAndSend(TITLE_EXCHANGE_NAME, TITLE_DELETE_KEY, name);
    }
}
