package com.stepien.aedes.controller.impl;

import com.stepien.aedes.controller.interfaces.ClassifierController;
import com.stepien.aedes.model.Classification;
import com.stepien.aedes.model.ClassificationParameters;
import com.stepien.aedes.service.ClassificationService;
import com.stepien.aedes.service.impl.ClassificationServiceImpl;
import com.stepien.aedes.util.OutbreakRisk;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/classifier")
public class ClassifierControllerImpl implements ClassifierController {

    private ClassificationService classificationService;
    final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public ClassifierControllerImpl(ClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    @GetMapping
    public ResponseEntity<Classification> classify(
            @RequestParam("location") String location, 
            @RequestParam("startDate") String startDateStr,
            @RequestParam("endDate") String endDateStr,
            @RequestParam("targetDate") String targetDateStr) throws ParseException {

        Date startDate = dateFormat.parse(startDateStr);
        Date endDate = dateFormat.parse(endDateStr);
        Date targetDate = dateFormat.parse(targetDateStr);

        ClassificationParameters parameters = new ClassificationParameters(location, startDate, endDate, targetDate);
        Classification classification = classificationService.classify(parameters);
        return new ResponseEntity<>(classification, HttpStatus.OK);
    }
}
