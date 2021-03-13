package com.stepien.aedes.repository;

import java.sql.Date;
import java.util.Collection;

import com.stepien.aedes.model.Identification;

import org.springframework.data.repository.CrudRepository;

public interface IdentificationRepository extends CrudRepository<Identification, String>{
    Collection<Identification> findAllByLocationId(String locationId);
    Collection<Identification> findAllByTimeBetween(
        Date dateStart,
        Date dateEnd
    );
    Collection<Identification> findAllByLocationIdAndTimeBetween(
        String locationId,
        java.util.Date startDate,
        java.util.Date endDate
    );

}
