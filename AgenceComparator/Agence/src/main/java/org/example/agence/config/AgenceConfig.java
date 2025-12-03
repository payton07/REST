package org.example.agence.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import java.util.Map;
import java.util.HashMap;

@Component
@ConfigurationProperties(prefix = "agence")
public class AgenceConfig {
    private Map<String, String> hotels = new HashMap<>();

    public Map<String, String> getHotels() {
        return hotels;
    }

    public void setHotels(Map<String, String> hotels) {
        this.hotels = hotels;
    }
}
