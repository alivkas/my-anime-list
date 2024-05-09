package com.example.myanimelist.config;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.binder.MeterBinder;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class MetricConfiguration {

    @Bean
    public MeterBinder meterBinder() {
        return registry -> {
            Counter.builder("request_count")
                    .description("Количество обращений")
                    .register(registry);
        };
    }
}
