package com.epam.gymappmainservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@Slf4j
@SpringBootApplication
@EnableFeignClients
public class GymAppMainServiceApplication {
    public static void main(String[] args) {

        SpringApplication.run(GymAppMainServiceApplication.class, args);

        log.info("\n\n swagger at: 'http://localhost:8080/swagger-ui/index.html'\n\n");
        log.info("\n\n api-docs at: 'http://localhost:8080/v3/api-docs'");

        log.info("\n\n------------- app started -------------\n\n");
    }
}
