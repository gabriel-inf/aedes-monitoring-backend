package com.stepien.aedes.dtos;


import java.util.ArrayList;
import java.util.List;

import com.stepien.aedes.model.GeoPoint;
import com.stepien.aedes.model.Polygon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PolygonDTO {
    private List<GeoPointDTO> points;

    public PolygonDTO(Polygon polygon) {
        this.points = new ArrayList<>();
        for (GeoPoint geoPoint : polygon.getGeoPoints()) {
            GeoPointDTO geoPointDTO = new GeoPointDTO(geoPoint);
            this.points.add(geoPointDTO);
        }
    }
}
