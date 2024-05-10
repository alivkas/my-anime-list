package com.example.myanimelist.web.controller;

import com.example.myanimelist.api.dto.TitleDto;
import com.example.myanimelist.api.service.TitleService;
import com.example.myanimelist.security.service.UserService;
import com.example.myanimelist.security.user.dto.UserDto;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/api/private/admin")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AdminController {

    UserService userServiceImpl;
    TitleService titleServiceImpl;

    @DeleteMapping
    public void deleteUser(@RequestParam String username) {
        userServiceImpl.deleteUser(username);
    }

    @GetMapping("/show")
    public UserDto getUser(@RequestParam String username) {
        return userServiceImpl.getUser(username);
    }

    @GetMapping("/show-all")
    public List<UserDto> getUsers() {
        return userServiceImpl.getAllUsers();
    }

    @PostMapping("/create-title")
    public void createTitle(@RequestBody @Valid TitleDto titleDto) {
        titleServiceImpl.addTitle(titleDto);
    }

    @GetMapping("/show-titles")
    public List<TitleDto> getTitles() {
        return titleServiceImpl.getTitles();
    }

    @GetMapping("/show-title")
    public TitleDto getTitle(@RequestParam String name) {
        return titleServiceImpl.getTitle(name);
    }

    @DeleteMapping("/delete-title")
    public void deleteTitle(@RequestParam String name) {
        titleServiceImpl.deleteTitle(name);
    }
}
