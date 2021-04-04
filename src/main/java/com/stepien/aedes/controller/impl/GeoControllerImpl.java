package com.stepien.aedes.controller.impl;

import java.util.ArrayList;
import java.util.List;

import com.stepien.aedes.dtos.ChunkDTO;
import com.stepien.aedes.model.Chunks;
import com.stepien.aedes.model.Location;
import com.stepien.aedes.repository.ChunkRepository;
import com.stepien.aedes.repository.LocationRepository;
import com.stepien.aedes.service.LocalizationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/geo")
public class GeoControllerImpl {

    Logger logger = LoggerFactory.getLogger(GeoControllerImpl.class);
    
    @Autowired
    private ChunkRepository chunkRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private LocalizationService localizationService;

    @PostMapping (value = "chunks")
    public ChunkDTO addChunk(@RequestBody ChunkDTO chunkDTO) {
        return createChunk(chunkDTO);
    }

    @PostMapping(value = "chunks/batch")
    public List<ChunkDTO> addChunks(@RequestBody List<ChunkDTO> chunk) {

        List<ChunkDTO> createdChunks = new ArrayList<>();
        List<Chunks> chunkModels = new ArrayList<>();

        for (ChunkDTO chunkDTO : chunk) {
            chunkModels.add(new Chunks(chunkDTO));
        }

        chunkModels = (List<Chunks>) chunkRepository.saveAll(chunkModels);
        
        for (Chunks chunks : chunkModels) {
            createdChunks.add(new ChunkDTO(chunks));
        }
        
        return createdChunks;
    }

    @GetMapping(value = "chunks")
    public List<ChunkDTO> getChunks() {
        List<ChunkDTO> chunksReturn = new ArrayList<>();
        List<Chunks> dbChunks = localizationService.getAllChunks();
        for (Chunks chunks : dbChunks) {
            ChunkDTO chunkDTO = new ChunkDTO(chunks);
            chunksReturn.add(chunkDTO);
        }
        return chunksReturn;
    }

    @PostMapping(value = "locations/batch")
    public List<Location> addLocations(@RequestBody List<Location> locations) {
        return (List<Location>) locationRepository.saveAll(locations);
    }

    @GetMapping(value = "locations")
    public List<Location> getLocations() {
        return (List<Location>) locationRepository.findAll();
    }

    private ChunkDTO createChunk(ChunkDTO chunkDTO) {
        Chunks chunkModel = new Chunks(chunkDTO);
        return new ChunkDTO(chunkRepository.save(chunkModel));
    }

}
