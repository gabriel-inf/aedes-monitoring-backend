package com.stepien.aedes.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.stepien.aedes.dtos.GridDTO;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Grid {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    @ElementCollection
    @Column(name = "latitudes")
    private List<Double> latitudes;
    @ElementCollection
    @Column(name = "longitudes")
    private List<Double> longitudes;

    public Grid(GridDTO gridDTO) {
        if (gridDTO.getId() != null) this.setId(gridDTO.getId());
        if (gridDTO.getLatitudes() != null) this.setLatitudes(gridDTO.getLatitudes());
        if (gridDTO.getLongitudes() != null) this.setLongitudes(gridDTO.getLongitudes());
    }

    public static List<Grid> modelListToDtoList(List<GridDTO> gridDtos) {
        List<Grid> gridModels = new ArrayList<>();
        for (GridDTO gridDTO : gridDtos) {
            Grid grid = new Grid(gridDTO);
            gridModels.add(grid);
        }
        return gridModels;
    }
}
