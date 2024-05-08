package com.example.myanimelist.web.controller;

import com.example.myanimelist.api.dto.TitleDto;
import com.example.myanimelist.api.service.TitleService;
import com.example.myanimelist.security.service.UserService;
import com.example.myanimelist.security.user.dto.UserDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void createTitle(@RequestBody TitleDto titleDto) {
        titleServiceImpl.addTitle(titleDto);
    }
}
