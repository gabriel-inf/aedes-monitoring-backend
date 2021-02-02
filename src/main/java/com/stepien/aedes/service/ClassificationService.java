package com.stepien.aedes.service;

import com.stepien.aedes.model.Classification;
import com.stepien.aedes.model.ClassificationParameters;

public interface ClassificationService {
    Classification classify(ClassificationParameters parameters);
}
