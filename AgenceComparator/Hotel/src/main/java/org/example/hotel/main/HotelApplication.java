package org.example.hotel.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {
        "org.example.hotel"
})
//EnableJpaRepositories annotation to specify the package for JPA repositories
@EnableJpaRepositories(basePackages = {
        "org.example.hotel.repository"
})
@SpringBootApplication(scanBasePackages = {
        "org.example.hotel.service", // Scanner le package du service
        "org.example.hotel.publisher",
        "org.example.hotel.model",
        "org.example.hotel.types",
        "org.example.hotel.repository",
        "org.example.hotel.controller"
        // "org.example.hotel.data"
        // Scanner le package de publication
})
public class HotelApplication {

    public static void main(String[] args) {
        SpringApplication.run(HotelApplication.class, args);
    }

}
