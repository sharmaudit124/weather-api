package com.example.weatherapi.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "weather")
@Data
public class WeatherApisProperties {
    private String accuWeatherApiKey;
    private String openWeatherApiKey;
}
