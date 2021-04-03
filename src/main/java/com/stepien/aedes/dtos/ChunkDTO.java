package com.stepien.aedes.dtos;

import com.stepien.aedes.model.Chunks;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChunkDTO {
    private GeoPointDTO bottomLeft;
    private GeoPointDTO bottomRight;
    private GeoPointDTO topLeft;
    private GeoPointDTO topRight;
    private GeoPointDTO centroid;
    private Double gridLine;
    private Double gridColumn;
    private String id;

    public ChunkDTO (Chunks chunkModel) {
        this.centroid = new GeoPointDTO(chunkModel.getCentroid());
        this.topLeft = new GeoPointDTO(chunkModel.getTopLeft());
        this.topRight = new GeoPointDTO(chunkModel.getTopRight());
        this.bottomLeft = new GeoPointDTO(chunkModel.getBottomLeft());
        this.bottomRight = new GeoPointDTO(chunkModel.getBottomRight());
        this.gridLine = chunkModel.getGridLine();
        this.gridColumn = chunkModel.getGridColumn();
        this.id = chunkModel.getId();
    }
}
