package com.stepien.aedes.jobs;

import com.stepien.aedes.repository.WeatherRepository;
import com.stepien.aedes.service.impl.WeatherInformationConsumer;
import com.stepien.aedes.service.impl.WeatherSyncJob;

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

    @Autowired
    WeatherSyncJob weatherSyncJob;


    @Scheduled(cron = "0 * * ? * *", zone = "America/Sao_Paulo")
    public void syncWeatherInformation() {
        logger.info("syncWeatherInformation() - Job Starting job");
        weatherSyncJob.processWeatherInformationForAllChunks();
        logger.info("syncWeatherInformation() - Job ending job");
    }
}
