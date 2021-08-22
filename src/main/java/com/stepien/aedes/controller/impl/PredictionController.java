package com.stepien.aedes.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.stepien.aedes.model.Chunks;
import com.stepien.aedes.model.PredictionIdentification;
import com.stepien.aedes.repository.ChunkRepository;
import com.stepien.aedes.repository.PredictionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping(value = "/prediction")
public class PredictionController {

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Autowired
    public PredictionRepository predictionRepository;

    @Autowired
    public ChunkRepository chunkRepository;

    @GetMapping(value="/{chunkId}")
    public Double getMethodName(
        @PathVariable String chunkId, 
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String date) throws ParseException {

        Date parsedDate = calculateSubmissionDate(date, "America/Sao_Paulo");

        if (!chunkRepository.findById(chunkId).isPresent()) {
            System.out.println("Chunk not found with id: " + chunkId);
        }
        Chunks chunk = chunkRepository.findById(chunkId).get();

        System.out.println("[DEBUG] Date: " + parsedDate);


        if (!predictionRepository.findById(new PredictionIdentification(chunk, parsedDate)).isPresent()) {
            System.out.println("Prediction not found: " + chunkId);
        }


        return predictionRepository.findById(new PredictionIdentification(chunk, parsedDate)).get().getValue();
    }
    

private synchronized Date calculateSubmissionDate(String dateString, String userTimeZone) throws ParseException {
      dateFormat.setTimeZone(TimeZone.getTimeZone(userTimeZone));
      return dateFormat.parse(dateString);
  }
    
}
