package com.stepien.aedes.model;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Polygon {
    @Id
    String id;

    @ElementCollection
    List<GeoPoint> points;

}
