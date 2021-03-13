package com.stepien.aedes.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Table(name = DangerCoordinates.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
public class DangerCoordinates {

    public static final String TABLE_NAME = "danger_coordinates";

    String id;

    Double lat;
    Double lgn;
    Date targetPredictionDate;
    Date dateOfPrediction;
}
