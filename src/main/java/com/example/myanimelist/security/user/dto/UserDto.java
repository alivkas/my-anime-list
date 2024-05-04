package com.example.myanimelist.security.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDto(@NotBlank String username, @Size(min = 5) @NotBlank String password) {
}
