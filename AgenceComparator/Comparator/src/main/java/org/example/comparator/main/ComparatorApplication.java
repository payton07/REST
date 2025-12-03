package org.example.comparator.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {
        "org.example.comparator.controller",
        "org.example.comparator.model",
        "org.example.comparator.repository",
        "org.example.comparator.config"
})
@EnableJpaRepositories("org.example.comparator.repository")
@EntityScan({"org.example.comparator.classes", "org.example.comparator.model"})
public class ComparatorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ComparatorApplication.class, args);
    }
}
