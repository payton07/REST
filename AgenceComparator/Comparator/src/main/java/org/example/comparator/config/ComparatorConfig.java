package org.example.comparator.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "comparator")
public class ComparatorConfig {
    private Map<String, String> agences = new HashMap<>();

    public Map<String, String> getAgences() {
        return agences;
    }

    public void setAgences(Map<String, String> agences) {
        this.agences = agences;
    }
}

