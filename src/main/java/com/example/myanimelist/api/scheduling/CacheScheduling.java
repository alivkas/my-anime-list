package com.example.myanimelist.api.scheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CacheScheduling {

    @Scheduled(cron = "${schedule.cron.time}", zone = "${schedule.cron.zone}")
    @CacheEvict(cacheNames = { "titles", "title_list", "users", "user_list" })
    public void cleanCache() {
        log.info("Cache cleaned");
    }
}
