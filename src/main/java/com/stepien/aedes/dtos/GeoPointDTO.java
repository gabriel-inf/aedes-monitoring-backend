package com.stepien.aedes.dtos;

import com.stepien.aedes.model.GeoPoint;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeoPointDTO {
    private double lat;
    private double lng;

    public GeoPointDTO(GeoPoint geoPoint) {
        this.lat = geoPoint.getLat();
        this.lng = geoPoint.getLgn();
    }
}
