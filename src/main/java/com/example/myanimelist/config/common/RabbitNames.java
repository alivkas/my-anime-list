package com.example.myanimelist.config.common;

public class RabbitNames {

    // EXCHANGES
    public static final String TITLE_EXCHANGE_NAME = "title-exchange";
    public static final String USER_EXCHANGE_NAME = "user-exchange";

    // QUEUES
    public static final String TITLE_CREATED_QUEUE_NAME = "title-create-queue";
    public static final String TITLE_DELETED_QUEUE_NAME = "title-delete-queue";
    public static final String USER_CREATED_QUEUE_NAME = "user-create-queue";
    public static final String USER_DELETED_QUEUE_NAME = "user-delete-queue";

    // ROUTING KEYS
    public static final String TITLE_CREATE_KEY = "create-title-key";
    public static final String TITLE_DELETE_KEY = "delete-title-key";
    public static final String USER_CREATE_KEY = "create-user-key";
    public static final String USER_DELETE_KEY = "delete-user-key";
}
