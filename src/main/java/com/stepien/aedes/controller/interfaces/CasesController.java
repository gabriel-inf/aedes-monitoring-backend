package com.stepien.aedes.controller.interfaces;

import com.stepien.aedes.model.Case;
import com.stepien.aedes.model.Classification;
import com.stepien.aedes.model.ClassificationParameters;
import com.stepien.aedes.service.ClassificationService;
import com.stepien.aedes.service.impl.ClassificationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public interface CasesController {

    Case addCase(Case newCase);
    Case editCase(Case newCase);
    Case getCase(String caseId);
    Collection<Case> getAllCases();
    Collection<Case> getAllCasesByLocation(String location);
    Collection<Case> getAllCasesByLocationAndPeriod(String location, Date startDate, Date endDate);

}
