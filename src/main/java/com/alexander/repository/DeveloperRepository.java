package com.alexander.repository;

import com.alexander.model.Developer;

import java.io.IOException;
import java.util.List;

public interface DeveloperRepository extends GenericRepository<Developer, Long> {
    void create(Developer developer) throws IOException;

    void delete(Long id) throws IOException;

    void update(Developer developer) throws IOException;

    Developer getById(Long id);

    List<Developer> getAll() throws IOException;

}
