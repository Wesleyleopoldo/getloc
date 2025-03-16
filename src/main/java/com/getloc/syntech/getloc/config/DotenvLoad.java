package com.getloc.syntech.getloc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.github.cdimascio.dotenv.Dotenv;

@Configuration
public class DotenvLoad {
    
    @Bean
    public Dotenv dotenv(){
        return Dotenv.load();
    }
}
