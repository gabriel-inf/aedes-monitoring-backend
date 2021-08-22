package com.stepien.aedes.repository;

import com.stepien.aedes.model.Prediction;
import com.stepien.aedes.model.PredictionIdentification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PredictionRepository extends JpaRepository<Prediction, PredictionIdentification>{
    
}
