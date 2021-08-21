package com.stepien.aedes.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stepien.aedes.repository.WeatherRepository;
import com.stepien.aedes.service.impl.WeatherInformationConsumer;
import com.stepien.aedes.service.impl.WeatherSyncJob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@CrossOrigin
@RestController
@RequestMapping("/develop")
public class DevelopController {
    Logger logger = LoggerFactory.getLogger(DevelopController.class);
    
    @Autowired
    WeatherInformationConsumer weatherInformationConsumer;

    @Autowired
    WeatherRepository weatherRepository;

    @Autowired
    WeatherSyncJob weatherSyncJob;

    @GetMapping(value = "/processWeather")
    public String processWeather() {
        weatherSyncJob.processWeatherInformationForAllChunks();
        return "Weather was processed!";
    }

}
