package com.stepien.aedes.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Table (name = Weather.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Weather {

    public static final String TABLE_NAME = "weather";

    @Id
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    public String id;

    @Column(nullable = false)
    private Date date;

    private Double avergeTemperature;
    private Double avergePressure;
    private Double avergeHumidity;
    private Double maxTemperature;
    private Double maxPressure;
    private Double maxHumidity;
    private Double minTemperature;
    private Double minPressure;
    private Double minHumidity;
    private Double rain;

    @JsonIgnoreProperties({"centroid", "bottomRight", "bottomLeft", "topRight", "topLeft"}) 
    @ManyToOne
    private Chunks chunk;

}
