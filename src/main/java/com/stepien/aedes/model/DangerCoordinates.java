package com.stepien.aedes.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    public String id;

    private Double lat;
    private Double lng;
    private Date targetPredictionDate;
    private Date dateOfPrediction;
}
