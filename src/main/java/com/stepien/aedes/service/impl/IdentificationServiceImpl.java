package com.stepien.aedes.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.stepien.aedes.model.Identification;
import com.stepien.aedes.repository.IdentificationRepository;
import com.stepien.aedes.service.IdentificationService;

import org.springframework.stereotype.Service;

@Service
public class IdentificationServiceImpl implements IdentificationService{

    IdentificationRepository identificationRepository;

    public IdentificationServiceImpl(IdentificationRepository identificationRepository) {
        this.identificationRepository = identificationRepository;
    }

    @Override
    public List<Identification> getIdentificationsBetween(Date startDate, Date endDate) {
        return (List<Identification>) getIdentificationRepository().findAllByTimeBetween(startDate, endDate);
    }

    @Override
    public List<Identification> getNeighborhoodIdentificationsBetween(Integer neighborhoodId, Date startDate, Date endDate) {
        return new ArrayList<>(getIdentificationRepository()
                 .findAllByLocationIdAndTimeBetween(neighborhoodId, startDate, endDate));
    }

    @Override
    public List<Identification> getChunkIdentificationsBetween(Integer gridLine, Integer gridColumn, Date startDate,
            Date endDate) {
        // TODO Auto-generated method stub
        return null;
    }

    public IdentificationRepository getIdentificationRepository() {
        return this.identificationRepository;
    }
    
}
