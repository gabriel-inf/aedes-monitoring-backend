package com.stepien.aedes.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = Location.TABLE_NAME)
public class Location {
    public static final String TABLE_NAME = "locations";
    
    @Id
    private String id;
    @Column(nullable = false)
    private String neighborhood;
    @Column(nullable = false)
    private String country;    

    @ElementCollection
    private List<Polygon> areas;
}
