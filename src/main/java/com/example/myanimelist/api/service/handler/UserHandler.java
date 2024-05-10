package com.example.myanimelist.api.service.handler;

import com.example.myanimelist.security.user.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.example.myanimelist.config.common.RabbitNames.*;

@Slf4j
@Component
public class UserHandler {

    @RabbitListener(queues = USER_CREATED_QUEUE_NAME)
    public void handleUserCreate(UserDto userDto) {
        log.info("USER INFO -----> <{}> ADDED", userDto.username());
    }

    @RabbitListener(queues = USER_DELETED_QUEUE_NAME)
    public void handleUserDelete(String username) {
        log.info("USER INFO -----> <{}> DELETED", username);
    }
}
