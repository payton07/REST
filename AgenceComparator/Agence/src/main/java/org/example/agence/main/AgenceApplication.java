package org.example.agence.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "org.example.agence.service", // Scanner le package du service
        "org.example.agence.controller", // Scanner le package des controllers REST
        "org.example.agence.config" // Scanner le package de configuration
})
public class AgenceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AgenceApplication.class, args);
    }
}