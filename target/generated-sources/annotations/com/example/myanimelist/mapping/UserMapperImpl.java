package com.example.myanimelist.mapping;

import com.example.myanimelist.security.entities.User;
import com.example.myanimelist.security.user.dto.UserDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-10T22:16:09+0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if ( user == null ) {
            return null;
        }

        String username = null;
        String email = null;
        String password = null;

        username = user.getUsername();
        email = user.getEmail();
        password = user.getPassword();

        UserDto userDto = new UserDto( username, email, password );

        return userDto;
    }
}
