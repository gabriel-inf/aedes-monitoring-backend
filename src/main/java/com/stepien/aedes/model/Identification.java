package com.stepien.aedes.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import javax.persistence.Id;
import javax.persistence.Table;

import com.stepien.aedes.dtos.IdentificationDTO;

import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
@Table(name = Identification.TABLE_NAME)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Identification {
    public static final String TABLE_NAME = "identifications";
    @Id
	@GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;
    private String locationId;

    @Column(nullable = false)
    private Double lat;

    @Column(nullable = false)
    private Double lgn;

    @Column(nullable = false)
    private Date time;

    public Identification(IdentificationDTO identificationDTO) {
        this.lat = identificationDTO.getLat();
        this.lgn = identificationDTO.getLgn();
        this.locationId = "TBD";
        this.time = new Date();
    }

}
