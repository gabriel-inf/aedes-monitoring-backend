package com.stepien.aedes.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChunksStatisticsDTO {
    private List<GenericInfoDTO> predictions;
    private List<GenericInfoDTO> history;
    private List<ChunksDetails> details;
}
