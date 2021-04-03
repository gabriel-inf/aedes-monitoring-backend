package com.stepien.aedes.service;

import com.stepien.aedes.model.GeoPoint;
import com.stepien.aedes.vo.GridPosition;

public interface LocalizationService {
    GridPosition getGridPosition(GeoPoint geoPoint);
}
