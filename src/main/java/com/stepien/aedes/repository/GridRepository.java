package com.stepien.aedes.repository;

import com.stepien.aedes.model.Grid;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GridRepository extends JpaRepository<Grid, String>{
    
}
