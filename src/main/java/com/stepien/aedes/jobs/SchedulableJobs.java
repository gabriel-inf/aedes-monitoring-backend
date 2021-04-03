package com.stepien.aedes.jobs;

import java.time.ZoneId;
import java.util.List;
import java.util.TimeZone;

import com.stepien.aedes.dtos.WeatherInformationDTO;
import com.stepien.aedes.model.Weather;
import com.stepien.aedes.repository.WeatherRepository;
import com.stepien.aedes.service.impl.WeatherInformationConsumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
// @EnableScheduling
@Configurable
public class SchedulableJobs {

    Logger logger = LoggerFactory.getLogger(SchedulableJobs.class);
    
    @Autowired
    WeatherInformationConsumer weatherInformationConsumer;

    @Autowired
    WeatherRepository weatherRepository;


    @Scheduled(cron = "0 0/1 * 1/1 * ?", zone = "America/Sao_Paulo")
    public void syncWeatherInformation() {
        logger.info("syncWeatherInformation() - Job Executed");

        List<WeatherInformationDTO> weatherInformationDTOs = weatherInformationConsumer.getCurrentWeatherInformation("29.50", "-51.1");

        for (WeatherInformationDTO weatherInformationDTO : weatherInformationDTOs) {
            logger.info(weatherInformationDTO.getWeather());
        }
    }
}
