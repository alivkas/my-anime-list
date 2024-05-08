package com.example.myanimelist.api.service;

import com.example.myanimelist.api.dto.TitleDto;
import com.example.myanimelist.api.entities.Title;

import java.util.List;

public interface TitleService {

    void addTitle(TitleDto titleDto);
    List<TitleDto> getTitles();
    TitleDto getTitle(String name);
    void deleteTitle(String name);
}
