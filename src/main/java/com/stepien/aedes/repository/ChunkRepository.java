package com.stepien.aedes.repository;


import com.stepien.aedes.model.Chunks;

import org.springframework.data.repository.CrudRepository;

public interface ChunkRepository extends CrudRepository<Chunks, String> {
}
