package com.example.myanimelist.security.service;

import com.example.myanimelist.security.user.dto.UserDto;
import com.example.myanimelist.security.entities.User;

public interface UserService {

    void createUser(UserDto userDto);
    void deleteUser(String username);
    User getUser(String username);
}
