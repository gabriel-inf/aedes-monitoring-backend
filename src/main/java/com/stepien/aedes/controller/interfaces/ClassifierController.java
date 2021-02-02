package com.stepien.aedes.controller.interfaces;

import com.stepien.aedes.model.Classification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;

public interface ClassifierController {
    ResponseEntity<Classification> classify(String location, String startDateStr, String endDateStr) throws ParseException;
}
