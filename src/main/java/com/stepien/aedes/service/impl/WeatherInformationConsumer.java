package com.stepien.aedes.service.impl;

import java.util.List;

import com.stepien.aedes.dtos.WeatherInformationDTO;

public interface WeatherInformationConsumer {
    List<WeatherInformationDTO> getCurrentWeatherInformation(Double lat, Double lng);
}
