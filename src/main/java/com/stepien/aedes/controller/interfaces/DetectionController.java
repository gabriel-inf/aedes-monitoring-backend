package com.stepien.aedes.controller.interfaces;

import com.stepien.aedes.model.Detection;

import java.util.Collection;
import java.util.Date;

public interface DetectionController {
    Detection addDetection(Detection newDetection);
    Detection editDetection(Detection newDetection);
    Detection getDetection(String DetectionId);
    Collection<Detection>getAllDetections();
    Collection<Detection>getAllDetectionsByLocation(String location);
    Collection<Detection>getAllDetectionsByLocationAndPeriod(String location, Date startDate, Date endDate);
}
