package com.stepien.aedes.controller.impl;

import java.util.List;

import com.stepien.aedes.dtos.GridDTO;
import com.stepien.aedes.model.Grid;
import com.stepien.aedes.repository.GridRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/grid")
public class GridController {

    @Autowired
    public GridRepository gridRepository;

    @GetMapping
    public List<GridDTO> getAllGrids() {
        return GridDTO.modelListToDtoList(gridRepository.findAll());
    }

    @PostMapping
    public GridDTO createGrid(@RequestBody GridDTO gridDto) {
        Grid gridModel = new Grid(gridDto);
        Grid saved = gridRepository.save(gridModel);
        return new GridDTO(saved);
    }
}
