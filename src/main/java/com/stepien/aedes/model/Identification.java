package com.stepien.aedes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = Identification.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Identification {
    public static final String TABLE_NAME = "identifications";
    @Id
    private String id;
    private String locationId;

    @Column(nullable = false)
    private Double lat;

    @Column(nullable = false)
    private Double lgn;

    @Column(nullable = false)
    private Date time;
}
