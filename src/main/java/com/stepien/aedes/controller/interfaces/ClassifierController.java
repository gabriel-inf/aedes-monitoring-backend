package com.stepien.aedes.controller.interfaces;

import com.stepien.aedes.model.Classification;
import org.springframework.http.ResponseEntity;
import java.text.ParseException;

public interface ClassifierController {
    ResponseEntity<Classification> classify(String location, String startDateStr, String endDateStr, String targetDateStr) throws ParseException;
}
