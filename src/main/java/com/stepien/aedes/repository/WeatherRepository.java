package com.stepien.aedes.repository;

import java.util.Date;
import java.util.List;

import com.stepien.aedes.model.Weather;

import org.springframework.data.repository.CrudRepository;

public interface WeatherRepository extends CrudRepository<Weather, String>{

    List<Weather> findByDate(Date date);
    
}
