package com.stepien.aedes.model;

import lombok.Data;


/**
 * Actions are applied to some location area to combat and prevent the mosquito spread
 */
@Data
public class Action {
    private GeoPoint geoPoint;
    private String name;
    private String description;
    private Double efficiency;
}
