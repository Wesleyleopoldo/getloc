package com.getloc.syntech.getloc.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.github.cdimascio.dotenv.Dotenv;

@Component
public class EnviromentsLoad {
    
    @Autowired
    private Dotenv dotenv;

    public String getUserId() {
        return dotenv.get("USER_ID");
    }

    public String getUserName() {
        return dotenv.get("USER_ROOT");
    }

    public String getPassword() {
        return dotenv.get("USER_PASSWORD");
    }

    public String getEmail() {
        return dotenv.get("USER_EMAIL");
    }
}
