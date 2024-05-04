package com.example.myanimelist.security.service;

import com.example.myanimelist.security.user.dto.UserDto;

import java.util.List;

public interface UserService {

    void createUser(UserDto userDto);
    void deleteUser(String username);
    UserDto getUser(String username);
    List<UserDto> getAllUsers();
}
