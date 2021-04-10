package com.stepien.aedes.controller.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.stepien.aedes.model.Weather;
import com.stepien.aedes.repository.GridRepository;
import com.stepien.aedes.repository.WeatherRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/weather")
public class WeatherController {

    private static final SimpleDateFormat dateFormat = 
    new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    public WeatherRepository weatherRepository;

    @GetMapping
    public List<Weather> getAllWeatherInfo() {
        List<Weather> w =  (List<Weather>) weatherRepository.findAll();
        System.out.println("Logger: " + w.get(0).getDate().toString());
        return w;
    }

    // Date format: yyyy-MM-dd
    @GetMapping(value = "/date")
    public List<Weather> getWeatherInfoByDate(
        @RequestParam("date") 
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String date
        ) throws ParseException {
            Date parsedDate = calculateSubmissionDate(date, "America/Sao_Paulo");
            return (List<Weather>) weatherRepository.findByDate(parsedDate);
    }

    private synchronized Date calculateSubmissionDate(String dateString, String userTimeZone) 
      throws ParseException {
        dateFormat.setTimeZone(TimeZone.getTimeZone(userTimeZone));
        return dateFormat.parse(dateString);
    }
}


