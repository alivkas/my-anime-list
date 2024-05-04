package com.example.myanimelist.security.user;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class Admin {

    public static String USERNAME;

    public static String PASSWORD;

    @Value("${superuser.admin.username}")
    public void setUsername(String username) {
        Admin.USERNAME = username;
    }

    @Value("${superuser.admin.password}")
    public void setPassword(String password) {
        Admin.PASSWORD = password;
    }
}
