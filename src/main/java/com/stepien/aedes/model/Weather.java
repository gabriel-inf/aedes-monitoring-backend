package com.stepien.aedes.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table (name = Weather.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Weather {

    public static final String TABLE_NAME = "weather";

    @Id
    public String id;

    @Column(nullable = false)
    private Date date;

    @Column(nullable = false)
    private String locationId;

    private Double avergeTemperature;
    private Double avergePressure;
    private Double avergeHumidity;
    private Double maxTemperature;
    private Double maxPressure;
    private Double maxHumidity;
    private Double minTemperature;
    private Double minPressure;
    private Double minHumidity;
}
