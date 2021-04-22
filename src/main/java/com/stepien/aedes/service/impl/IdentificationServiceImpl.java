package com.stepien.aedes.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;


import com.stepien.aedes.dtos.IdentificationDayCountDTO;
import com.stepien.aedes.dtos.IdentificationDayCountDTOImpl;
import com.stepien.aedes.dtos.IdentificationsPerLocationDto;
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
    public List<IdentificationsPerLocationDto> getNumberOfIdentificationPerLocationBetween(Date startDate, Date endDate) {
        return new ArrayList<>(getIdentificationRepository()
                 .getNumberOfIdentificationPerLocationBetween(startDate, endDate));
    }

    @Override
    public List<Identification> getChunkIdentificationsBetween(Integer gridLine, Integer gridColumn, Date startDate,
            Date endDate) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<IdentificationDayCountDTO> getIdentificationDayCounts(Date startDate, Date endDate) {


        LocalDate start = convertToLocalDateViaInstant(startDate);
        LocalDate end = convertToLocalDateViaInstant(endDate);
        List<Date> totalDates = new ArrayList<>();
        while (!start.isAfter(end)) {
            totalDates.add(convertToDateViaSqlDate(start));
            start = start.plusDays(1);
        }
        

        List<IdentificationDayCountDTO> countByDay = new ArrayList<>(getIdentificationRepository()
                 .getIdentificationDayCounts(startDate, endDate));


        List<IdentificationDayCountDTO> countByDayWithAllDays = new ArrayList<>();

        boolean found;
        IdentificationDayCountDTO foundElement;
        for (Date day : totalDates) {
            found = false;
            foundElement = null;
            for (IdentificationDayCountDTO identificationDayCountDTO : countByDay) {
                if (isSameDay(identificationDayCountDTO.getDay(), day)) {
                    found = true;
                    foundElement = identificationDayCountDTO;
                    break;
                }
            }
            if (!found) {
                countByDayWithAllDays.add(new IdentificationDayCountDTOImpl(0, day));
            } else {
                countByDayWithAllDays.add(foundElement);
            }
        }
        return countByDayWithAllDays;
    }


    public IdentificationRepository getIdentificationRepository() {
        return this.identificationRepository;
    }

    private LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
          .atZone(ZoneId.systemDefault())
          .toLocalDate();
    }

    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    private boolean isSameDay(Date d1, Date d2) {
        String date1 = d1.toString();
        String date2 = d2.toString();
        return date1.contains(date2);
    }

    
}
