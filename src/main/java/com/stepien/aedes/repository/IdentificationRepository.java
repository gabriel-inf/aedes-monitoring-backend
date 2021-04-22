package com.stepien.aedes.repository;

import java.util.Date;
import java.util.Collection;

import com.stepien.aedes.dtos.IdentificationDayCountDTO;
import com.stepien.aedes.dtos.IdentificationsPerLocationDto;
import com.stepien.aedes.model.Identification;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IdentificationRepository extends CrudRepository<Identification, String>{

    String GET_ALL_BY_LOCATION_AND_DATE_RANGE = 
        """
            select distinct
                ident.*
            from
                locations l inner join
                chunks_intersects ci on (l.code = ci.intersection) inner join
                chunks c on (c.id = ci.chunks_id) inner join
                identifications ident on (ident.grid_line = c.grid_line and ident.grid_column = c.grid_column)
            where
                l.code = :locationCode and
                ident.time between :startDate and :endDate
        """;

    String GET_ALL_LOCATION_COUNT_BETWEEN_PERIOD = 
        """
            select distinct
                l.code as locationCode,
                l.neighborhood as locationName,
                count(ident.id) as numberOfIdentificationInThePeriod,
                current_date as periodStart,
                current_timestamp as periodEnd
            from
                locations l inner join
                chunks_intersects ci on (l.code = ci.intersection) inner join
                chunks c on (c.id = ci.chunks_id) inner join
                identifications ident on (ident.grid_line = c.grid_line and ident.grid_column = c.grid_column)
            where
                ident.time between :startDate and :endDate
            group by
                l.code, l.neighborhood
            order by
                numberOfIdentificationInThePeriod desc
        """;

    String IDENTIFICATIONS_BY_DAY = 
        """
            select
                count(i.id) numIdentifications,
                date_trunc('day', i.time) as day
            from
                identifications i 
            where
                i.time between :startDate and :endDate
            group by
                day
            order by
                day asc
        """;


    Collection<Identification> findAllByLocationId(String locationId);
    Collection<Identification> findAllByTimeBetween(
        Date dateStart,
        Date dateEnd
    );

    @Query(value = GET_ALL_BY_LOCATION_AND_DATE_RANGE, nativeQuery = true)
    Collection<Identification> findAllByLocationIdAndTimeBetween(
            @Param("locationCode") Integer locationCode,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

    @Query(value = GET_ALL_LOCATION_COUNT_BETWEEN_PERIOD, nativeQuery = true)
    Collection<IdentificationsPerLocationDto> getNumberOfIdentificationPerLocationBetween(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

    @Query(value = IDENTIFICATIONS_BY_DAY, nativeQuery = true)
    Collection<IdentificationDayCountDTO> getIdentificationDayCounts(
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate
    );

}
