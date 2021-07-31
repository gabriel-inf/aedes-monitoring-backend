package com.stepien.aedes.service.impl;


import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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

        for (Chunks chunk : this.chunks) {
            weatherInformationConsumer.getCurrentWeatherInformation(chunk, yesterday); // poor naming, we get the weather information for the previous day
        }
    }
    
}
