package com.stepien.aedes.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;


@Entity
@Table (name = Weather.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Weather {

    public static final String TABLE_NAME = "weather";

    @Id
    String id;

    @Column(nullable = false)
    Date date;

    @Column(nullable = false)
    String locationId;

    Double avergeTemperature;
    Double avergePressure;
    Double avergeHumidity;
    Double maxTemperature;
    Double maxPressure;
    Double maxHumidity;
    Double minTemperature;
    Double minPressure;
    Double minHumidity;
}
