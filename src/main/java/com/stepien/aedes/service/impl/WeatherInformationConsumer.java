package com.stepien.aedes.service.impl;

import java.util.Date;

import com.stepien.aedes.dtos.WeatherAPIReturnDTO;
import com.stepien.aedes.model.Chunks;

public interface WeatherInformationConsumer {
    WeatherAPIReturnDTO getCurrentWeatherInformation(Chunks chunk, Date date);
}
