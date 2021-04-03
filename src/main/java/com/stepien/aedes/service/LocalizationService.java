package com.stepien.aedes.service;

import java.util.List;

import com.stepien.aedes.repository.GridRepository;

import org.springframework.beans.factory.annotation.Autowired;



public interface LocalizationService {
    List<Double> latitudesInOrder();
}
