package com.stepien.aedes.service.impl;

import com.stepien.aedes.model.Chunks;
import com.stepien.aedes.model.GeoPoint;



import com.stepien.aedes.repository.ChunkRepository;
import com.stepien.aedes.service.LocalizationService;
import com.stepien.aedes.vo.GridPosition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocalizationServiceImpl implements LocalizationService{

    @Autowired
    ChunkRepository chunkRepository;

    @Override
    public GridPosition getGridPosition(GeoPoint geoPoint) {
        List<Chunks> chunks = (List<Chunks>) chunkRepository.findAll();

        System.out.println(geoPoint.getLat().toString() + ", " + geoPoint.getLng().toString());
        for (Chunks chunk : chunks) {
            if (isPointInsideChunk(geoPoint, chunk)) {
                return new GridPosition(chunk.getGridLine(), chunk.getGridColumn());
            }
        }
        return null;
    }

    private boolean isPointInsideChunk(GeoPoint geoPoint, Chunks chunk) {     
        return  geoPoint.getLat() >= chunk.getTopLeft().getLat() &&
                geoPoint.getLat() < chunk.getBottomLeft().getLat() &&
                geoPoint.getLng() >= chunk.getBottomLeft().getLng() &&
                geoPoint.getLng() < chunk.getBottomRight().getLng();
    }
    
}
