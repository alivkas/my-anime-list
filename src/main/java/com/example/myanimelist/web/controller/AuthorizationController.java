package com.example.myanimelist.web.controller;

import com.example.myanimelist.security.user.dto.UserDto;
import com.example.myanimelist.security.service.UserService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("public/api")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AuthorizationController {

    UserService userServiceImpl;

    @PostMapping("/registration")
    public void signUp(@RequestBody UserDto userDto) {
        userServiceImpl.createUser(userDto);
    }

    @GetMapping
    public String test() {
        return "Public page";
    }
}
