package com.stepien.aedes.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HourlyWeatherDTO {
    private Date dt;
    private Double temp;
    private Double feels_like;
    private Double pressure;
    private Double humidity;
    private Double dew_point;
    private Integer clouds;
    private Integer visibility;
    private Double wind_speed;
    private Double wind_deg;
    private HourlyRainDTO rain;
}