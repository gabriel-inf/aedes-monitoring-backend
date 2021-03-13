package com.stepien.aedes.controller.interfaces;

import com.stepien.aedes.model.Case;
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
