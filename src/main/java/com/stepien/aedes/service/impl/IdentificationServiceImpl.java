package com.stepien.aedes.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;

import java.util.Date;
import java.util.List;


import com.stepien.aedes.dtos.IdentificationDayCountDTO;
import com.stepien.aedes.dtos.IdentificationDayCountDTOImpl;
import com.stepien.aedes.dtos.IdentificationsPerLocationDto;
import com.stepien.aedes.model.Chunks;
import com.stepien.aedes.model.GeoPoint;
import com.stepien.aedes.model.Identification;
import com.stepien.aedes.model.Trap;
import com.stepien.aedes.repository.IdentificationRepository;
import com.stepien.aedes.repository.TrapRepository;
import com.stepien.aedes.service.IdentificationService;
import com.stepien.aedes.service.LocalizationService;
import com.stepien.aedes.vo.GridPosition;

import org.springframework.stereotype.Service;

@Service
public class IdentificationServiceImpl implements IdentificationService{

    IdentificationRepository identificationRepository;
    TrapRepository trapRepository;
    LocalizationService localizationService;

    public IdentificationServiceImpl(
        IdentificationRepository identificationRepository, 
        TrapRepository trapRepository,
        LocalizationService localizationService
    ) {
        this.identificationRepository = identificationRepository;
        this.trapRepository = trapRepository;
        this.localizationService = localizationService;
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

    @Override
    public void addTrapIdentifications(String trapId, Integer numberOfIdentifications) {
        Trap trap = trapRepository
            .findById(trapId)
            .orElseThrow(() -> new IllegalArgumentException("Trap not found"));
        Date today = getToday();
        Date trapLastFeed = getTrapLastFeedDate(trap);
        GeoPoint trapLocation = trap.getLocation();
        applyAverageToAllDays(numberOfIdentifications, trapLastFeed, today, trapLocation);
        trap.setLastTimeFeed(today);
        trapRepository.save(trap);
    }

    private void applyAverageToAllDays(Integer numberOfIdentifications, Date startDate, Date endDate, GeoPoint location) {
        List<Date> allDatesToAddIdentifications = getAllDatesBetweenToDates(startDate, endDate);
        int daysInDivision = allDatesToAddIdentifications.size();
        int value = (int) numberOfIdentifications / (daysInDivision > 0 ? daysInDivision : 1);
        allDatesToAddIdentifications.forEach(date -> {
            Identification identification = new Identification();
            identification.setLat(location.getLat());
            identification.setLng(location.getLng());
            identification.setTime(date);
            saveIdentificationNTimes(value, identification);
        });
    }

    private void saveIdentificationNTimes(int n, Identification identification) {
        for (int i=0; i<n; i++) {
            Identification identificationToSave = new Identification();
            identificationToSave.setLat(identification.getLat());
            identificationToSave.setLng(identification.getLng());
            identificationToSave.setTime(identification.getTime());
            populateIdentificationInfo(identificationToSave);
            if(identificationToSave.getChunk() == null) return;
            getIdentificationRepository().save(
                identificationToSave
            );
        }
    }

    private List<Date> getAllDatesBetweenToDates(Date startDate, Date endDate) {
        LocalDate start = convertToLocalDateViaInstant(startDate);
        LocalDate end = convertToLocalDateViaInstant(endDate);
        List<Date> totalDates = new ArrayList<>();

        start = start.plusDays(1);
        while (!start.isAfter(end)) {
            totalDates.add(convertToDateViaSqlDate(start));
            start = start.plusDays(1);
        }
        return totalDates;
    }

    private Date getToday() {
        return new Date();
    }

    private Double getNumberOfDaysBetweenDates(Date start, Date end) {
        return (((end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24)) + 1D);
    }

    private Date getTrapLastFeedDate(Trap trap) {
        return trap.getLastTimeFeed() != null ? trap.getLastTimeFeed() : new Date();
    }

    public void populateIdentificationInfo(Identification identification) {
        GeoPoint identificationPoint = new GeoPoint(identification.getLat(), identification.getLng());
        GridPosition gridPosition = localizationService.getGridPosition(identificationPoint);
        Chunks chunk = localizationService.getChunkPosition(identificationPoint);
        if (gridPosition != null) {
            identification.setGridLine(gridPosition.getGridLine());
            identification.setGridColumn(gridPosition.getGridColumn());
            identification.setChunk(chunk);
        }
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
