package com.stepien.aedes.repository;

import java.util.Date;
import java.util.List;

import com.stepien.aedes.model.Prediction;
import com.stepien.aedes.model.PredictionIdentification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PredictionRepository extends JpaRepository<Prediction, PredictionIdentification>{
    List<Prediction> findByIdentificationDate(Date parsedDate);   
}
