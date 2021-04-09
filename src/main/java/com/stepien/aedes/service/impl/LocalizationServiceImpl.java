package com.stepien.aedes.service.impl;

import com.stepien.aedes.model.Chunks;
import com.stepien.aedes.model.GeoPoint;



import com.stepien.aedes.repository.ChunkRepository;
import com.stepien.aedes.service.LocalizationService;
import com.stepien.aedes.vo.GridPosition;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Scope("singleton")
public class LocalizationServiceImpl implements LocalizationService{

    Logger logger = LoggerFactory.getLogger(LocalizationServiceImpl.class);

    public static List<Chunks> cachedChunks;

    @Autowired
    ChunkRepository chunkRepository;

    @Override
    public GridPosition getGridPosition(GeoPoint geoPoint) {
        processChunksCache();
        List<Chunks> chunks = cachedChunks;

        System.out.println(geoPoint.getLat().toString() + ", " + geoPoint.getLng().toString());
        for (Chunks chunk : chunks) {
            if (isPointInsideChunk(geoPoint, chunk)) {
                return new GridPosition(chunk.getGridLine(), chunk.getGridColumn());
            }
        }
        return null;
    }

    @Override
    public List<Chunks> getAllChunks() {
        logger.info("Getting chunks");
        processChunksCache();
        return cachedChunks;
    }

    private void processChunksCache() {
        if (cachedChunks == null) {
            cachedChunks = (List<Chunks>) chunkRepository.findAll();
        }
    }

    private boolean isPointInsideChunk(GeoPoint geoPoint, Chunks chunk) {     
        return  geoPoint.getLat() >= chunk.getTopLeft().getLat() &&
                geoPoint.getLat() < chunk.getBottomLeft().getLat() &&
                geoPoint.getLng() >= chunk.getBottomLeft().getLng() &&
                geoPoint.getLng() < chunk.getBottomRight().getLng();
    }
    
}
