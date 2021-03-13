package com.stepien.aedes.service.impl;

import com.stepien.aedes.model.Classification;
import com.stepien.aedes.model.ClassificationParameters;
import com.stepien.aedes.service.ClassificationService;

import org.springframework.stereotype.Service;

@Service
public class ClassificationServiceImpl implements ClassificationService {
    @Override
    public Classification classify(ClassificationParameters parameters) {
        return new Classification();
    }
}
