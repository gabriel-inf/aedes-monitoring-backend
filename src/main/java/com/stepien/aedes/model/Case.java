package com.stepien.aedes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = Case.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
public class Case {
    public static final String TABLE_NAME = "dengue_cases";

    @Id
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private Double lat;
    private Double lng;
    private Date date;
    private String place;
}
