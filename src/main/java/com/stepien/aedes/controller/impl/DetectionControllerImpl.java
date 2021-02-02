package com.stepien.aedes.controller.impl;

import com.stepien.aedes.controller.interfaces.DetectionController;
import com.stepien.aedes.model.Detection;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.Collection;
import java.util.Date;

@CrossOrigin
@RestController
@RequestMapping("/detection")
public class DetectionControllerImpl implements DetectionController {

    @Override
    public HttpResponse<Detection> addDetection(Detection newDetection) {
        return null;
    }

    @Override
    public HttpResponse<Detection> editDetection(Detection newDetection) {
        return null;
    }

    @Override
    public HttpResponse<Detection> getDetection(String DetectionId) {
        return null;
    }

    @Override
    public HttpResponse<Collection<Detection>> getAllDetections() {
        return null;
    }

    @Override
    public HttpResponse<Collection<Detection>> getAllDetectionsByLocation(String location) {
        return null;
    }

    @Override
    public HttpResponse<Collection<Detection>> getAllDetectionsByLocationAndPeriod(String location, Date startDate, Date endDate) {
        return null;
    }
}
