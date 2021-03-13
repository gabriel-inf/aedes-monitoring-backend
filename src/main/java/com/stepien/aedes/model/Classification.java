package com.stepien.aedes.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.Table;

import com.stepien.aedes.util.OutbreakRisk;
import com.stepien.aedes.util.OutbreakStage;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "classifications")
public class Classification {
    @Id
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private Integer numberOfIdentifications;
    private OutbreakStage predictedStage;
    private OutbreakRisk predictedRisk;

    @Column(nullable = false)
    private Date dateOfPrediction;

    @Column(nullable = false)
    private Date targetPredictionDate;
}
