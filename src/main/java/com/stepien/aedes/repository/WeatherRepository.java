package com.stepien.aedes.repository;

import com.stepien.aedes.model.Weather;

import org.springframework.data.repository.CrudRepository;

public interface WeatherRepository extends CrudRepository<Weather, String>{
    
}
