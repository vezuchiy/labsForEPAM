package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
public class MainApplicationClass {
    public static void main(String[] args) {
        SpringApplication.run(MainApplicationClass.class);
    }
}
