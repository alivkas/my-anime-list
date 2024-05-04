package com.example.myanimelist.mapping;

import com.example.myanimelist.security.entities.User;
import com.example.myanimelist.security.user.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toUserDto(User user);
}
