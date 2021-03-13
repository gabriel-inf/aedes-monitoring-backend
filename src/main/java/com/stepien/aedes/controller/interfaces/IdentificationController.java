package com.stepien.aedes.controller.interfaces;

import com.stepien.aedes.model.Identification;

import java.util.Collection;
import java.util.Date;

public interface IdentificationController {
    Identification addIdentification(Identification newIdentification);
    Identification editIdentification(Identification newIdentification);
    Identification getIdentification(String IdentificationId);
    Collection<Identification>getAllIdentifications();
    Collection<Identification>getAllIdentificationsByLocation(String location);
    Collection<Identification>getAllIdentificationsByLocationAndPeriod(String location, Date startDate, Date endDate);
}
