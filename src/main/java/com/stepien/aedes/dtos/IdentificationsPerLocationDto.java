package com.stepien.aedes.dtos;

import java.util.Date;


public interface IdentificationsPerLocationDto {
    Integer getLocationCode();
    String getLocationName();
    Integer getNumberOfIdentificationInThePeriod();
    Date getPeriodStart();
    Date getPeriodEnd();
    Double getLat();
    Double getLng();
}
