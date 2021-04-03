package com.stepien.aedes.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.stepien.aedes.dtos.GeoPointDTO;
import com.stepien.aedes.dtos.PolygonDTO;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Polygon {

    @Id
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<GeoPoint> geoPoints;

    public Polygon(PolygonDTO polygonDTO) {
        List<GeoPointDTO> geoPointDTOs = polygonDTO.getPoints();
        this.geoPoints = new ArrayList<>();

        for (GeoPointDTO geoPointDTO : geoPointDTOs) {
            GeoPoint geoPoint = new GeoPoint(geoPointDTO);
            this.geoPoints.add(geoPoint);
        }
    }

}
