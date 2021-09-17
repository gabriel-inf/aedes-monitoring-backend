package com.stepien.aedes.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = Location.TABLE_NAME)
public class Location {
    public static final String TABLE_NAME = "locations";
    
    @Id
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private Integer code;

    @Column(nullable = true)
    private String neighborhood;
    @Column(nullable = true)
    private String country;

    @OneToOne(cascade=CascadeType.PERSIST)
    private GeoPoint centroid;

    // @Column
    // private Double lat;

    // @Column
    // private Double lng;

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Polygon> polygons;
}
