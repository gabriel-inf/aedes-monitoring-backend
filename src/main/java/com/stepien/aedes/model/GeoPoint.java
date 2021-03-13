package com.stepien.aedes.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table( name = GeoPoint.TABLE_NAME )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoPoint {

    public static final String TABLE_NAME = "geo_points";

    @Id
    private String id;
    private Double lat;
    private Double lgn;
}
