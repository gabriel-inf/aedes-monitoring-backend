package com.stepien.aedes.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChunksDetails {
    String id;
    Double numberOfPredicted;
    Double numberOfHistory;
}
