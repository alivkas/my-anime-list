package com.example.myanimelist.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record TitleDto(@NotBlank String name,
                       @NotBlank String date,
                       @NotBlank String studio,
                       @NotEmpty List<String> genres) {
}
