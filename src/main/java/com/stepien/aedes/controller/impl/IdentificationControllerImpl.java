package com.stepien.aedes.controller.impl;

import com.stepien.aedes.controller.interfaces.IdentificationController;
import com.stepien.aedes.dtos.IdentificationDTO;
import com.stepien.aedes.dtos.IdentificationDayCountDTO;
import com.stepien.aedes.dtos.IdentificationsPerLocationDto;
import com.stepien.aedes.dtos.LocationPeriodDTO;
import com.stepien.aedes.model.GeoPoint;
import com.stepien.aedes.model.Identification;
import com.stepien.aedes.repository.IdentificationRepository;
import com.stepien.aedes.service.IdentificationService;
import com.stepien.aedes.service.LocalizationService;
import com.stepien.aedes.vo.GridPosition;

import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/identifications")
public class IdentificationControllerImpl implements IdentificationController {

    private static final SimpleDateFormat dateFormat =
            new SimpleDateFormat("yyyy-MM-dd");

    private IdentificationRepository identificationRepository;
    private LocalizationService localizationService;
    private IdentificationService identificationService;

    public IdentificationControllerImpl(IdentificationRepository identificationRepository,
                                        LocalizationService localizationService,
                                        IdentificationService identificationService) {
        this.identificationRepository = identificationRepository;
        this.localizationService = localizationService;
        this.identificationService = identificationService;
    }

    @Override
    @PostMapping
    public Identification addIdentification(@RequestBody IdentificationDTO newIdentification) {
        Identification identification = new Identification(newIdentification);
        
        GridPosition gridPosition = localizationService.getGridPosition(new GeoPoint(newIdentification.getLat(), newIdentification.getLng()));

        if (gridPosition != null) {
            identification.setGridLine(gridPosition.getGridLine());
            identification.setGridColumn(gridPosition.getGridColumn());
            return (Identification) getIdentificationRepository().save(identification);
        }
        return null;
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

        if (newIdentification.getLng() != null) oldIdentification.setLng(newIdentification.getLng());
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
    public Collection<Identification> getAllIdentifications() {
        return (Collection<Identification>) getIdentificationRepository().findAll();
    }

    @GetMapping("/location/{location}")
    public Collection<Identification> getAllIdentificationsByLocation(@PathVariable String location) {
        return getIdentificationRepository().findAllByLocationId(location);
    }

    @GetMapping("/locationDate")
    @Override
    public Collection<Identification> getAllIdentificationsByLocationAndPeriod(@RequestBody LocationPeriodDTO locationPeriodDTO) {
        return getIdentificationRepository().findAllByLocationIdAndTimeBetween(Integer.valueOf(locationPeriodDTO.getLocationId()), locationPeriodDTO.getStartDate(), locationPeriodDTO.getEndDate());
    }

    @GetMapping("getLocationIdentificationsBetweenDates")
    public Collection<Identification> getLocationIdentificationsBetweenDates(
            @RequestParam Integer locationId,
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        try {

            Date formatedEndDate = dateFormat.parse(endDate);
            formatedEndDate.setHours(23);
            formatedEndDate.setMinutes(59);
            formatedEndDate.setSeconds(59);

            return getIdentificationService().getNeighborhoodIdentificationsBetween(
                    locationId,
                    dateFormat.parse(startDate),
                    formatedEndDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("getAllIdentificationsBetweenDates")
    public Collection<Identification> getAllIdentificationsBetweenDates(
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        try {

            Date endDateFormatter = dateFormat.parse(endDate);

            Calendar c = Calendar.getInstance(); 
            c.setTime(endDateFormatter); 
            c.add(Calendar.DATE, 1);
            endDateFormatter = c.getTime();
            
            return getIdentificationService().getIdentificationsBetween(
                    dateFormat.parse(startDate),
                    endDateFormatter);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("getNumberOfIdentificationPerLocationBetween")
    public Collection<IdentificationsPerLocationDto> getNumberOfIdentificationPerLocationBetween(
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        try {

            Date formatterDate = dateFormat.parse(endDate);
            formatterDate.setHours(23);
            formatterDate.setMinutes(59);
            formatterDate.setSeconds(59);
            

            return getIdentificationService().getNumberOfIdentificationPerLocationBetween(
                    dateFormat.parse(startDate),
                    formatterDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    @GetMapping("getIdentificationDayCounts")
    public Collection<IdentificationDayCountDTO> getIdentificationDayCounts(
            @RequestParam String startDate,
            @RequestParam String endDate
    ) {
        try {

            Date formatterDate = dateFormat.parse(endDate);
            formatterDate.setHours(23);
            formatterDate.setMinutes(59);
            formatterDate.setSeconds(59);
            

            return getIdentificationService().getIdentificationDayCounts(
                    dateFormat.parse(startDate),
                    formatterDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public IdentificationRepository getIdentificationRepository() {
        return this.identificationRepository;
    }
    public IdentificationService getIdentificationService(){
        return this.identificationService;
    }

}
