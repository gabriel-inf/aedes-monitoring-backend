package com.stepien.aedes.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Configuration
@PropertySource("classpath:apis.properties")
@Data
public class WeatherAPIProperties {
    
    @Value("${weather.clientId}")
    private String clientId;

    @Value("${weather.clientSecret}")
    private String clientSecret;
}
