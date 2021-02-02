package com.stepien.aedes.model;

import lombok.Data;

import java.util.Date;

@Data
public class Case {
    private GeoPoint location;
    private Date date;
    private String place;
}
