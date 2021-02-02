package com.stepien.aedes.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class ClassificationParameters {
    private String location;
    private Date startDate;
    private Date endDate;
}
