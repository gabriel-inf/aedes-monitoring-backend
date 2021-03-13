package com.stepien.aedes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClassificationParameters {
    private String locationId;
    private Date startDate;
    private Date endDate;
    private Date targetDate;
}
