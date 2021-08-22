package com.stepien.aedes.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Entity
@Data
public class Prediction{

    // @Id
    // @ManyToOne
    // private Chunks chunk;
    private Double value;

    // @Id
    // @Column(nullable = false)
    // private Date date;


    @EmbeddedId
    private PredictionIdentification identification;    
}
