package com.stepien.aedes.dtos;

import java.util.ArrayList;
import java.util.List;

import com.stepien.aedes.model.Grid;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GridDTO {
    private String id;
    private List<Double> latitudes;
    private List<Double> longitudes;

    public GridDTO(Grid grid) {
        this.id = grid.getId();
        this.latitudes = grid.getLatitudes();
        this.longitudes = grid.getLongitudes();
    }

    public static List<GridDTO> modelListToDtoList(List<Grid> gridModels) {
        List<GridDTO> gridDtos = new ArrayList<>();
        for (Grid gridModel : gridModels) {
            GridDTO gridDTO = new GridDTO(gridModel);
            gridDtos.add(gridDTO);
        }
        return gridDtos;
    }
}
