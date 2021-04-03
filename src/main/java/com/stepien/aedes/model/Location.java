package com.stepien.aedes.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

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
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    @Column(nullable = false)
    private String neighborhood;
    @Column(nullable = false)
    private String country;    

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Polygon> polygons;
}
