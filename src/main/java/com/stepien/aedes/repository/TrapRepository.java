package com.stepien.aedes.repository;

import java.util.List;
import java.util.Optional;

import com.stepien.aedes.model.Trap;

import org.springframework.data.repository.CrudRepository;

public interface TrapRepository extends CrudRepository<Trap, String>{

    Optional<Trap> findByName(String name);
    
}
