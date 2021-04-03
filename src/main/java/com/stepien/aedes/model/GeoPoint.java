package com.stepien.aedes.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.Table;

import com.stepien.aedes.dtos.GeoPointDTO;

import org.hibernate.annotations.GenericGenerator;

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

    public GeoPoint(GeoPointDTO geoPointDTO) {
        this.lat = geoPointDTO.getLat();
        this.lgn = geoPointDTO.getLng();
    }

    @Id
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private Double lat;
    private Double lgn;
}
