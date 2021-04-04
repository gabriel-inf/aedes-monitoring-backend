package com.stepien.aedes.service;

import java.util.List;

import com.stepien.aedes.model.Chunks;
import com.stepien.aedes.model.GeoPoint;
import com.stepien.aedes.vo.GridPosition;

public interface LocalizationService {
    GridPosition getGridPosition(GeoPoint geoPoint);
    List<Chunks> getAllChunks();
}
