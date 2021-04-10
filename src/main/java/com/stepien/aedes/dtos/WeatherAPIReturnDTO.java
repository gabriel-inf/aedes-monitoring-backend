package com.stepien.aedes.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WeatherAPIReturnDTO {
    private Double lat;
    private Double lon;
    private String timezone;
    private List<HourlyWeatherDTO> hourly; 
}
