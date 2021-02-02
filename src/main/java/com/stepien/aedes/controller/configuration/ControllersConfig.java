package com.stepien.aedes.controller.configuration;

import com.stepien.aedes.service.ClassificationService;
import com.stepien.aedes.service.impl.ClassificationServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import static org.springframework.context.annotation.ScopedProxyMode.TARGET_CLASS;

@Configuration
public class ControllersConfig {

    @Bean
    @Scope(value="request", proxyMode=TARGET_CLASS)
    public ClassificationService getClassificationService() {
        return new ClassificationServiceImpl();
    }

}
