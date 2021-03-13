package com.stepien.aedes.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = DangerCoordinates.TABLE_NAME)
@NoArgsConstructor
@AllArgsConstructor
public class DangerCoordinates {

    public static final String TABLE_NAME = "danger_coordinates";

    @Id
    public String id;

    private Double lat;
    private Double lgn;
    private Date targetPredictionDate;
    private Date dateOfPrediction;
}
