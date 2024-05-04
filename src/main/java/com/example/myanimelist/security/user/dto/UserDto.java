package com.example.myanimelist.security.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserDto(@NotBlank String username, @NotBlank @Email String email, @Size(min = 5) @NotBlank String password) {
}
