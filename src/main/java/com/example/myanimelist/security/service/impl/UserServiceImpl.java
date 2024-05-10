package com.example.myanimelist.security.service.impl;

import com.example.myanimelist.mapping.UserMapper;
import com.example.myanimelist.security.user.dto.UserDto;
import com.example.myanimelist.security.entities.User;
import com.example.myanimelist.security.repository.UserRepository;
import com.example.myanimelist.security.repository.UserRoleRepository;
import com.example.myanimelist.security.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import static com.example.myanimelist.config.common.RabbitNames.*;

import javax.management.relation.RoleNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Transactional
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserRoleRepository userRoleRepository;
    PasswordEncoder passwordEncoder;
    RabbitTemplate rabbitTemplate;

    @SneakyThrows
    @Override
    public void createUser(UserDto userDto) {
        User user = new User();

        user.setUsername(userDto.username());
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setAuthorities(Collections.singleton(userRoleRepository
                .findByRole("ROLE_USER")
                .orElseThrow(RoleNotFoundException::new)));
        user.setEmail(userDto.email());

        userRepository.save(user);

        rabbitTemplate.convertAndSend(USER_EXCHANGE_NAME, USER_CREATE_KEY, userDto);
    }

    @SneakyThrows
    @Override
    @CacheEvict(cacheNames = { "users", "user_list" })
    public void deleteUser(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new UsernameNotFoundException("Username not found");
        }
        userRepository.deleteByUsername(username);

        rabbitTemplate.convertAndSend(USER_EXCHANGE_NAME, USER_DELETE_KEY, username);
    }

    @Override
    @Cacheable(cacheNames = "users")
    public UserDto getUser(String username) {
        User user = userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));

        return UserMapper.INSTANCE.toUserDto(user);
    }

    @Override
    @Cacheable(cacheNames = "user_list")
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        for (User user : users) {
            UserDto userDto = UserMapper.INSTANCE.toUserDto(user);
            userDtoList.add(userDto);
        }

        return userDtoList;
    }
}
