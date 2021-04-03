package com.stepien.aedes.controller.interfaces;

import com.stepien.aedes.dtos.IdentificationDTO;
import com.stepien.aedes.dtos.LocationPeriodDTO;
import com.stepien.aedes.model.Identification;

import java.util.Collection;

public interface IdentificationController {
    Identification addIdentification(IdentificationDTO newIdentification);
    Identification editIdentification(IdentificationDTO newIdentification);
    Identification getIdentification(String IdentificationId);
    Collection<Identification>getAllIdentifications();
    Collection<Identification>getAllIdentificationsByLocation(String location);
    Collection<Identification>getAllIdentificationsByLocationAndPeriod(LocationPeriodDTO locationPeriodDTO);
}
