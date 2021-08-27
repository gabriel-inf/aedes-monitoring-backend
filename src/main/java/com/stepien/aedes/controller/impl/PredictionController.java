package com.stepien.aedes.controller.impl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.TimeZone;
import java.util.stream.Collectors;

import com.stepien.aedes.model.Chunks;
import com.stepien.aedes.model.Prediction;
import com.stepien.aedes.model.PredictionIdentification;
import com.stepien.aedes.repository.ChunkRepository;
import com.stepien.aedes.repository.PredictionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Double> getMethodName(
        @PathVariable String chunkId, 
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String date) throws ParseException {

        Date parsedDate = calculateSubmissionDate(date, "America/Sao_Paulo");
        ResponseEntity<Double> response;
        if (!chunkRepository.findById(chunkId).isPresent()) {
            System.out.println("Chunk not found with id: " + chunkId);
        }

        try {
            Chunks chunk = chunkRepository.findById(chunkId).get();
            Double value = predictionRepository.findById(new PredictionIdentification(chunk, parsedDate)).get().getValue();
            response = new ResponseEntity<>(value, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            response = new ResponseEntity<>(0d, HttpStatus.NOT_FOUND);
        }
        return response;
    }

    @GetMapping
    public ResponseEntity<Map<String, Double>> getAllPredictions(
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) String date, 
        @RequestParam int daysInFuture) throws ParseException {
        
        Date parsedDate = calculateSubmissionDate(date, "America/Sao_Paulo");
        Map<String, Double> result = null;
        List<Prediction> predictions = predictionRepository.findByIdentificationDate(parsedDate);
        if (predictions != null && predictions.size() > 0) {
            result = predictions.stream()
                .collect(Collectors.toMap(
                    prediction -> prediction.getIdentification().getChunk().getId(),
                    prediction -> prediction.getValue()
                ));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    

    private synchronized Date calculateSubmissionDate(String dateString, String userTimeZone) throws ParseException {
        dateFormat.setTimeZone(TimeZone.getTimeZone(userTimeZone));
        return dateFormat.parse(dateString);
    }
    
}
