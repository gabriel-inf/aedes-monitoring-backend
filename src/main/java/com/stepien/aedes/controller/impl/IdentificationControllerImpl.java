package com.stepien.aedes.controller.impl;

import com.stepien.aedes.controller.interfaces.IdentificationController;
import com.stepien.aedes.dtos.IdentificationDTO;
import com.stepien.aedes.dtos.LocationPeriodDTO;
import com.stepien.aedes.model.Identification;
import com.stepien.aedes.repository.IdentificationRepository;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/identifications")
public class IdentificationControllerImpl implements IdentificationController {

    private IdentificationRepository identificationRepository;

    public IdentificationControllerImpl(IdentificationRepository identificationRepository) {
        this.identificationRepository = identificationRepository;
    }

    @Override
    @PostMapping
    public void addIdentification(@RequestBody IdentificationDTO newIdentification) {
        Identification identification = new Identification(newIdentification);
        getIdentificationRepository().save(identification);
    }

    @Override
    @PatchMapping
    public Identification editIdentification(@RequestBody IdentificationDTO newIdentification) {
        Identification oldIdentification = null;
        Optional<Identification> identificationFromDB = getIdentificationRepository().findById(newIdentification.getId());
        if (identificationFromDB.isPresent()) {
            oldIdentification = identificationFromDB.get();
        }

        if (oldIdentification == null) return null; // exit doing nothing

        if (newIdentification.getLgn() != null) oldIdentification.setLgn(newIdentification.getLgn());
        if (newIdentification.getLat() != null) oldIdentification.setLat(newIdentification.getLat());
        if (newIdentification.getLocationId() != null) oldIdentification.setLocationId(newIdentification.getLocationId());
        if (newIdentification.getTime() != null) oldIdentification.setTime(newIdentification.getTime());

        getIdentificationRepository().save(oldIdentification);
        return oldIdentification;
        
    }

    @GetMapping(value = "/{identificationId}")
    @Override
    public Identification getIdentification(@PathVariable String identificationId) {
        Optional<Identification> optionalIdentification = getIdentificationRepository().findById(identificationId);
        Identification identification = null;
        if (optionalIdentification.isPresent()) {
            identification = optionalIdentification.get();
        }

        return identification;
    }

    @GetMapping
    @Override
    public Collection<Identification>getAllIdentifications() {
        return (Collection<Identification>) getIdentificationRepository().findAll();
    }

    @GetMapping("/location/{location}")
    public Collection<Identification>getAllIdentificationsByLocation(@PathVariable String location) {
        return getIdentificationRepository().findAllByLocationId(location);
    }

    @GetMapping("/locationDate")
    @Override
    public Collection<Identification>getAllIdentificationsByLocationAndPeriod(@RequestBody LocationPeriodDTO locationPeriodDTO) {
        return getIdentificationRepository().findAllByLocationIdAndTimeBetween(locationPeriodDTO.getLocationId(), locationPeriodDTO.getStartDate(), locationPeriodDTO.getEndDate());
    }

    public IdentificationRepository getIdentificationRepository() {
        return this.identificationRepository;
    }

}
