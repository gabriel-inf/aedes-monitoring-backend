package com.stepien.aedes.controller.interfaces;

import com.stepien.aedes.model.Case;
import com.stepien.aedes.model.Classification;
import com.stepien.aedes.model.ClassificationParameters;
import com.stepien.aedes.service.ClassificationService;
import com.stepien.aedes.service.impl.ClassificationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

public interface CasesController {

    HttpResponse<Case> addCase(Case newCase);
    HttpResponse<Case> editCase(Case newCase);
    HttpResponse<Case> getCase(String caseId);
    HttpResponse<Collection<Case>> getAllCases();
    HttpResponse<Collection<Case>> getAllCasesByLocation(String location);
    HttpResponse<Collection<Case>> getAllCasesByLocationAndPeriod(String location, Date startDate, Date endDate);

}
