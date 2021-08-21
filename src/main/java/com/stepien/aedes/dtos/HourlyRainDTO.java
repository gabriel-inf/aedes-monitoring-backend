package com.stepien.aedes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HourlyRainDTO {
    // the last hour amount of rainfall in millimeters
    private Double lh;
}
