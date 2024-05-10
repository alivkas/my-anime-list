package com.example.myanimelist.api.service.handler;

import com.example.myanimelist.api.dto.TitleDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import static com.example.myanimelist.config.common.RabbitNames.*;

@Slf4j
@Component
public class TitleHandler {

    @RabbitListener(queues = TITLE_CREATED_QUEUE_NAME)
    public void handleCreateTitle(TitleDto titleDto) {
        log.info("TITLE INFO -----> <{}> ADDED", titleDto.name());
    }

    @RabbitListener(queues = TITLE_DELETED_QUEUE_NAME)
    public void handleDeleteTitle(String name) {
        log.info("TITLE INFO -----> <{}> DELETED", name);
    }
}
