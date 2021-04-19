package com.stepien.aedes.service;

import java.util.Date;
import java.util.List;

import com.stepien.aedes.model.Identification;

public interface IdentificationService {
    List<Identification> getIdentificationsBetween(Date startDate, Date endDate);
    List<Identification> getNeighborhoodIdentificationsBetween(String neighborhoodId, Date startDate, Date endDate);
    List<Identification> getChunkIdentificationsBetween(Integer gridLine, Integer gridColumn, Date startDate, Date endDate);
}
