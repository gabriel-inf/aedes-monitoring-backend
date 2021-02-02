package com.stepien.aedes.model;

import com.stepien.aedes.util.OutbreakRisk;
import com.stepien.aedes.util.OutbreakStage;
import lombok.Data;

@Data
public class Classification {
    private String id;
    private OutbreakStage predictedStage;
    private OutbreakRisk predictedRisk;
    private String location;
}
