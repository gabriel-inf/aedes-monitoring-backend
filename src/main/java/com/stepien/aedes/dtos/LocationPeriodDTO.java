package com.stepien.aedes.dtos;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationPeriodDTO {
    private String locationId;
    private Date startDate;
    private Date endDate;
}
