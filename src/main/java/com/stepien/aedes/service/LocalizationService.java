package com.stepien.aedes.service;

import java.util.List;

import com.stepien.aedes.model.Chunks;
import com.stepien.aedes.model.GeoPoint;
import com.stepien.aedes.vo.GridPosition;

public interface LocalizationService {
    GridPosition getGridPosition(GeoPoint geoPoint);
    
    /**
     * Returns all the chunks that are in the system, this way we can display than in the frontend
     * @param geoPoint
     * @return
     */
    List<Chunks> getAllChunks();
}
