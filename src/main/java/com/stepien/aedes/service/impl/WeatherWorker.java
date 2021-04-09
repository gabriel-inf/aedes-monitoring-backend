package com.stepien.aedes.service.impl;

import java.util.List;

import com.stepien.aedes.dtos.WeatherInformationDTO;
import com.stepien.aedes.model.Chunks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WeatherWorker implements Runnable {

    private Logger logger = LoggerFactory.getLogger(WeatherWorker.class);

    private List<Chunks> chunks;
    private WeatherInformationConsumer weatherInformationConsumer;

    public WeatherWorker(List<Chunks> chunks, WeatherInformationConsumer weatherInformationConsumer) {
        this.chunks = chunks;
        this.weatherInformationConsumer = weatherInformationConsumer;
    }

    @Override
    public void run() {
        for (Chunks chunk : this.chunks) {
            logger.warn("Size: " + chunk.getId());
            logger.warn(weatherInformationConsumer.toString());
            List<WeatherInformationDTO> weatherInformationDTOs = weatherInformationConsumer.getCurrentWeatherInformation(chunk.getCentroid().getLat(), chunk.getCentroid().getLng());
            logger.warn("weatherInformationDTOs: " + weatherInformationDTOs.size());
            for (WeatherInformationDTO weatherInformationDTO : weatherInformationDTOs) {
                logger.info(weatherInformationDTO.getWeather());
            }
        }
    }
    
}
