package com.example.myanimelist.security.service.impl;

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
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.Collections;

@Transactional
@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserRoleRepository userRoleRepository;
    PasswordEncoder passwordEncoder;

    @SneakyThrows
    @Override
    public void createUser(UserDto userDto) {
        User user = new User();

        user.setUsername(userDto.username());
        user.setPassword(passwordEncoder.encode(userDto.password()));
        user.setAuthorities(Collections.singleton(userRoleRepository
                .findByRole("ROLE_USER")
                .orElseThrow(RoleNotFoundException::new)));

        userRepository.save(user);
    }

    @SneakyThrows
    @Override
    public void deleteUser(String username) {
        if (!userRepository.existsByUsername(username)) {
            throw new UsernameNotFoundException("Username not found");
        }
        userRepository.deleteByUsername(username);
    }

    @Override
    public User getUser(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
