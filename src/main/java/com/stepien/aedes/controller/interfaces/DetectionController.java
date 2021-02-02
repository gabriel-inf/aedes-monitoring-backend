package com.stepien.aedes.controller.interfaces;

import com.stepien.aedes.model.Detection;

import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.Date;

public interface DetectionController {
    HttpResponse<Detection> addDetection(Detection newDetection);
    HttpResponse<Detection> editDetection(Detection newDetection);
    HttpResponse<Detection> getDetection(String DetectionId);
    HttpResponse<Collection<Detection>> getAllDetections();
    HttpResponse<Collection<Detection>> getAllDetectionsByLocation(String location);
    HttpResponse<Collection<Detection>> getAllDetectionsByLocationAndPeriod(String location, Date startDate, Date endDate);
}
