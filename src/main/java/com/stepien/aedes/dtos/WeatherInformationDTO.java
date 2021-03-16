package com.stepien.aedes.dtos;

import java.sql.Timestamp;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherInformationDTO {
    private Timestamp timestamp;
    private Double tempC;
    Double feelsLikeC;
    Double dewPointC;
    Double humidity;
    Double pressureMB;
    Double pressureIN;
    Double windDirDEG;
    Double windSpeedKPH;
    Double precipMM;
    Double sky;
    String weather;
    Double solradWM2;
}
