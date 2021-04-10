package com.stepien.aedes;

import java.util.TimeZone;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class AedesApplication {

	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone("America/Sao_Paulo"));
		SpringApplication.run(AedesApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
    	return new RestTemplate();
	}

	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
    return jacksonObjectMapperBuilder -> 
        jacksonObjectMapperBuilder.timeZone(TimeZone.getTimeZone("America/Sao_Paulo"));
	}


}
