package com.example.myanimelist.config;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.example.myanimelist.config.common.RabbitNames.*;

@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RabbitConfiguration {

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Queue queueForCreatedTitles() {
        return new Queue(TITLE_CREATED_QUEUE_NAME, false);
    }

    @Bean
    public Queue queueForDeletedTitles() {
        return new Queue(TITLE_DELETED_QUEUE_NAME, false);
    }

    @Bean
    public Queue queueForCreatedUsers() {
        return new Queue(USER_CREATED_QUEUE_NAME, false);
    }

    @Bean
    public Queue queueForDeletedUsers() {
        return new Queue(USER_DELETED_QUEUE_NAME, false);
    }

    @Bean
    public TopicExchange topicExchangeTitle() {
        return new TopicExchange(TITLE_EXCHANGE_NAME);
    }

    @Bean
    public TopicExchange topicExchangeUser() {
        return new TopicExchange(USER_EXCHANGE_NAME);
    }

    @Bean
    public Binding bindingCreateTitle(Queue queueForCreatedTitles, TopicExchange topicExchangeTitle) {
        return BindingBuilder.bind(queueForCreatedTitles).to(topicExchangeTitle).with(TITLE_CREATE_KEY);
    }

    @Bean
    public Binding bindingDeleteTitle(Queue queueForDeletedTitles, TopicExchange topicExchangeTitle) {
        return BindingBuilder.bind(queueForDeletedTitles).to(topicExchangeTitle).with(TITLE_DELETE_KEY);
    }

    @Bean
    public Binding bindingCreateUser(Queue queueForCreatedUsers, TopicExchange topicExchangeUser) {
        return BindingBuilder.bind(queueForCreatedUsers).to(topicExchangeUser).with(USER_CREATE_KEY);
    }

    @Bean
    public Binding bindingDeleteUser(Queue queueForDeletedUsers, TopicExchange topicExchangeUser) {
        return BindingBuilder.bind(queueForDeletedUsers).to(topicExchangeUser).with(USER_DELETE_KEY);
    }
}
