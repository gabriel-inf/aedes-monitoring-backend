package com.stepien.aedes.controller.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.stepien.aedes.dtos.TrapIdentificationsDTO;
import com.stepien.aedes.model.Trap;
import com.stepien.aedes.model.Weather;
import com.stepien.aedes.repository.GridRepository;
import com.stepien.aedes.repository.IdentificationRepository;
import com.stepien.aedes.repository.TrapRepository;
import com.stepien.aedes.repository.WeatherRepository;
import com.stepien.aedes.service.IdentificationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/trap")
public class TrapController {

    @Autowired
    public IdentificationRepository identificationRepository;

    @Autowired
    public TrapRepository trapRepository;

    @Autowired
    public IdentificationService identificationService;

    @PostMapping
    public Trap addTrap(@RequestBody Trap trap) {
        System.out.println("this is a trap: " + trap.getName());
        System.out.println("lat: " + trap.getLocation().getLat());
        System.out.println("lng: " + trap.getLocation().getLng());
        return trapRepository.save(trap);
    }

    @PostMapping(value = "/identification")
    public void addTrapIdentifications(@RequestBody TrapIdentificationsDTO trapIdentifications) {
        System.out.println("this is a trap: " + trapIdentifications.getTrapName());
        identificationService.addTrapIdentifications(trapIdentifications.getTrapName(), trapIdentifications.getNumberOfIdentifications());
    }


}


