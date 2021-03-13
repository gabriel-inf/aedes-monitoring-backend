package com.stepien.aedes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = Case.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
public class Case {
    public static final String TABLE_NAME = "case";
    private Double lat;
    private Double lgn;
    private Date date;
    private String place;
}
