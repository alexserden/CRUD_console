package com.alexander.repository;

import com.alexander.model.Developer;

import java.io.IOException;
import java.util.List;

public interface DeveloperRepository extends GenericRepository<Developer,Long>{
    Developer create(Developer developer) throws IOException;

    void delete(Long id);

    Developer getById(Long id);

    List<Developer> getAll() throws IOException;

    void clearAll();
}
