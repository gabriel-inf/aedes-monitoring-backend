package com.stepien.aedes.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.stepien.aedes.dtos.HourlyWeatherDTO;
import com.stepien.aedes.dtos.WeatherAPIReturnDTO;
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

        LocalDateTime date = LocalDateTime.now().minusDays(1);
        Date yesterday = Date.from(date.atZone(ZoneId.of("America/Sao_Paulo")).toInstant());
        logger.info("yesterday: " + yesterday.getTime()/1000);

        for (Chunks chunk : this.chunks) {
            WeatherAPIReturnDTO weatherInformationDTOs = weatherInformationConsumer.getCurrentWeatherInformation(chunk.getCentroid().getLat(), chunk.getCentroid().getLng(), yesterday);
            
            for (HourlyWeatherDTO hw : weatherInformationDTOs.getHourly()) {
                logger.info(hw.toString());
            }
        }
    }
    
}
