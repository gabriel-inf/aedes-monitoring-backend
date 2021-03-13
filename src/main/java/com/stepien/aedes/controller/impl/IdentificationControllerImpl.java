package com.stepien.aedes.controller.impl;

import com.stepien.aedes.controller.interfaces.IdentificationController;
import com.stepien.aedes.model.Identification;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/Identification")
public class IdentificationControllerImpl implements IdentificationController {

    @Override
    public Identification addIdentification(Identification newIdentification) {
        return null;
    }

    @Override
    public Identification editIdentification(Identification newIdentification) {
        return null;
    }

    @Override
    public Identification getIdentification(String IdentificationId) {
        return null;
    }

    @Override
    public Collection<Identification>getAllIdentifications() {
        return null;
    }

    @Override
    public Collection<Identification>getAllIdentificationsByLocation(String location) {
        return null;
    }

    @Override
    public Collection<Identification>getAllIdentificationsByLocationAndPeriod(String location, Date startDate, Date endDate) {
        return null;
    }
}
