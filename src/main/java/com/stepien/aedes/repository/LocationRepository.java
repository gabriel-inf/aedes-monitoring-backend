package com.stepien.aedes.repository;



import java.util.Date;
import java.util.List;

import com.stepien.aedes.dtos.GenericInfoDTO;
import com.stepien.aedes.model.Location;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends CrudRepository<Location, String>{
    
    // get the number of chunks that the given location      
    String CHUNKS_PER_LOCATION_ID = 
        """  
            select 
                count(*) 
            from 
                locations l inner join
                chunks_intersects ci on (ci.intersection = l.code) inner join
                chunks c on (c.id = ci.chunks_id)
            where
                l.code = :locationCode
        """;

    String LOCATION_CHUNKS = 
    """  
        select 
            c.id 
        from 
            locations l inner join
            chunks_intersects ci on (ci.intersection = l.code) inner join
            chunks c on (c.id = ci.chunks_id)
        where
            l.code = :locationCode
    """;

    String IDENTIFICATIONS_PER_LOCATION_AND_PERIOD = 
        """ 
            select 
                count(i.id) 
            from 
                locations l inner join
                chunks_intersects ci on (ci.intersection = l.code) inner join
                chunks c on (c.id = ci.chunks_id) inner join
                identifications i on (i.chunk_id = c.id)
            where
                i.time between :startDate and :endDate and
                l.code = :locationCode
        """;

    String PREDICTIONS_PER_LOCATION_AND_PERIOD =
    """ 
        select 
            COALESCE(sum(p.value))
        from 
            locations l inner join
            chunks_intersects ci on (ci.intersection = l.code) inner join
            chunks c on (c.id = ci.chunks_id) inner join
            prediction p on (p.chunk_id = c.id)
        where
            p.date between :startDate and :endDate and
            l.code = :locationCode
        group by
            l.code
    """;
    
    String GET_NUMBER_OF_CHUNKS_WITH_IDENTIFICATIONS_BETWEEN_PERIOD =
        """ 
            select count(T.chunk_id) 
            from (
                select distinct
                    p.chunk_id
                from 
                    locations l inner join
                    chunks_intersects ci on (ci.intersection = l.code) inner join
                    chunks c on (c.id = ci.chunks_id) inner join
                    prediction p on (p.chunk_id = c.id)
                where
                    p.date between :startDate and :endDate and
                    l.code = :locationCode and
                    p.value > 0
            )T
        
        """;

    String GET_LOCATION_CHUNKS_IDENTIFICATIONS =
        """ 
        select
            count(*) \"value\",
            i.chunk_id \"id\",
            date_trunc('day', i.\"time\") \"day\"
        from 
            locations l inner join
            chunks_intersects ci on (ci.\"intersection\" = l.code) inner join
            chunks c on (c.id = ci.chunks_id) inner join
            identifications i on (i.chunk_id = c.id)
        where
        	l.code = :locationCode and
            i.\"time\" between :startDate and :endDate
        group by
            i.chunk_id,
            \"day\"
        """;

    String GET_LOCATION_CHUNKS_PREDICTIONS_BETWEEN =
        """ 
        select
            count(*) \"value\",
            p.chunk_id \"id\",
            date_trunc('day', p.\"date\") \"day\"
        from 
            locations l inner join
            chunks_intersects ci on (ci.\"intersection\" = l.code) inner join
            chunks c on (c.id = ci.chunks_id) inner join
            prediction p on (p.chunk_id = c.id)
        where
            l.code = :locationCode and
            p.\"date\" between :startDate and :endDate
        group by
            p.chunk_id,
            \"day\"
        """;

    @Query(value = CHUNKS_PER_LOCATION_ID, nativeQuery = true)
    Integer getNumberOfChunksPerLocationId(Integer locationCode);

    @Query(value = LOCATION_CHUNKS, nativeQuery = true)
    List<String> getLocationChunks(Integer locationCode);

    @Query(value = IDENTIFICATIONS_PER_LOCATION_AND_PERIOD, nativeQuery = true)
    Integer getNumberOfIdentificationsPerLocationCodeAndPeriod(
        @Param("locationCode") Integer locationCode,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate
    );

    @Query(value = PREDICTIONS_PER_LOCATION_AND_PERIOD, nativeQuery = true)
    Integer getPredictionsByLocationIdAndPeriod(
        @Param("locationCode") Integer locationCode,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate
    );

    @Query(value = GET_NUMBER_OF_CHUNKS_WITH_IDENTIFICATIONS_BETWEEN_PERIOD, nativeQuery = true)
    Integer getNumberOfChunksWithIdentificationsForecastByLocationIdBetweenPeriod(
        @Param("locationCode") Integer locationCode,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate
    );

    @Query(value = GET_LOCATION_CHUNKS_IDENTIFICATIONS, nativeQuery = true)
    List<GenericInfoDTO> getLocationChunkIdentifications(
        @Param("locationCode") Integer locationCode,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate
    );

    @Query(value = GET_LOCATION_CHUNKS_PREDICTIONS_BETWEEN, nativeQuery = true)
    List<GenericInfoDTO> getLocationChunksPredictionsBetween(
        @Param("locationCode") Integer locationCode,
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate
    );

}
