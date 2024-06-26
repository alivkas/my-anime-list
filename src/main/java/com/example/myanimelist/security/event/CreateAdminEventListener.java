package com.example.myanimelist.security.event;

import com.example.myanimelist.security.entities.User;
import com.example.myanimelist.security.repository.UserRepository;
import com.example.myanimelist.security.repository.UserRoleRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleInfoNotFoundException;

import java.util.Collections;

import static com.example.myanimelist.security.user.Admin.*;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class CreateAdminEventListener {

    UserRoleRepository userRoleRepository;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    @Async
    @SneakyThrows
    @EventListener
    public void handleStartApplicationContext(ContextRefreshedEvent event) {
        if (!userRepository.existsByUsername(USERNAME)) {
            User admin = new User();

            admin.setUsername(USERNAME);
            admin.setPassword(passwordEncoder.encode(PASSWORD));
            admin.setAuthorities(Collections.singleton(userRoleRepository
                    .findByRole("ROLE_ADMIN")
                    .orElseThrow(RoleInfoNotFoundException::new)));
            admin.setEmail(EMAIL);

            userRepository.save(admin);

            Thread.sleep(1000);
            log.info("Admin create");
        } else {
            Thread.sleep(1000); // Для вывода после компиляции
            log.info("Admin exists");
        }
    }
}
