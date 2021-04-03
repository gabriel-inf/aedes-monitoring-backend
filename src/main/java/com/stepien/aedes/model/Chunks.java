package com.stepien.aedes.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.stepien.aedes.dtos.ChunkDTO;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Chunks
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = Chunks.TABLE_NAME)
public class Chunks {

    public static final String TABLE_NAME = "chunks";

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String id;

    private Integer gridLine;
    private Integer gridColumn;

    @OneToOne(cascade=CascadeType.PERSIST)
    private GeoPoint centroid;

    @OneToOne(cascade=CascadeType.PERSIST)
    private GeoPoint topLeft;

    @OneToOne(cascade=CascadeType.PERSIST)
    private GeoPoint topRight;

    @OneToOne(cascade=CascadeType.PERSIST)
    private GeoPoint bottomLeft;

    @OneToOne(cascade=CascadeType.PERSIST)
    private GeoPoint bottomRight;

    @ElementCollection
    @Column(name = "intersection")
    private List<Integer> intersects;

    public Chunks (ChunkDTO chunkDTO) {
        this.centroid = new GeoPoint(chunkDTO.getCentroid());
        this.topLeft = new GeoPoint(chunkDTO.getTopLeft());
        this.topRight = new GeoPoint(chunkDTO.getTopRight());
        this.bottomLeft = new GeoPoint(chunkDTO.getBottomLeft());
        this.bottomRight = new GeoPoint(chunkDTO.getBottomRight());
        this.gridLine = chunkDTO.getGridLine();
        this.gridColumn = chunkDTO.getGridColumn();
        this.id = chunkDTO.getId();
        this.intersects = chunkDTO.getIntersects();
    }

}