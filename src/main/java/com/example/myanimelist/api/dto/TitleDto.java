package com.example.myanimelist.api.dto;

import java.util.List;

public record TitleDto(String name,
                       String date,
                       String studio,
                       List<String> genres) {
}
