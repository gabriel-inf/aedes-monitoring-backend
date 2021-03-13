package com.stepien.aedes.repository;

import com.stepien.aedes.model.Location;

import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, String>{
    
}
