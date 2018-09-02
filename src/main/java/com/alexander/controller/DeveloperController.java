package com.alexander.controller;

import com.alexander.model.Developer;
import com.alexander.repository.DeveloperRepository;
import com.alexander.repository.impl.JavaIODeveloperRepositoryImpl;

import java.io.IOException;
import java.util.List;

public class DeveloperController {
    static DeveloperRepository developerRepository;

    public DeveloperController() throws IOException {
        developerRepository = new JavaIODeveloperRepositoryImpl();
    }

    public void create(Developer developer) throws IOException {
         developerRepository.create(developer);
    }

    public void update(Developer developer) throws IOException {

        developerRepository.update(developer);

    }

    public void delete(Long id) throws IOException {
        developerRepository.delete(id);
    }


    public Developer getById(Long id) throws IOException {
        return developerRepository.getById(id);
    }


    public List<Developer> getAll() throws IOException {
        return developerRepository.getAll();
    }

}
