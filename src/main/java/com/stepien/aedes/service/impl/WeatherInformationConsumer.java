package com.stepien.aedes.service.impl;

import java.util.Date;
import java.util.List;

import com.stepien.aedes.dtos.WeatherAPIReturnDTO;

public interface WeatherInformationConsumer {
    WeatherAPIReturnDTO getCurrentWeatherInformation(Double lat, Double lng, Date date);
}
