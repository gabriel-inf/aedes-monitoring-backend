package com.stepien.aedes.dtos;

import java.util.Date;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IdentificationDayCountDTOImpl implements IdentificationDayCountDTO{
   
    public Integer numIdentifications;
    public Date day;
    
    @Override
    public Integer getNumIdentifications() {
        return numIdentifications;
    }
    @Override
    public Date getDay() {
        return this.day;
    }
}
