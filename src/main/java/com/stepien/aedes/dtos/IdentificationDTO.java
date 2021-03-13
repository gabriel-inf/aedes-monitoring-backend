package com.stepien.aedes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IdentificationDTO {
    private String id;
    private String locationId;
    private Double lat;
    private Double lgn;
    private Date time;
}
