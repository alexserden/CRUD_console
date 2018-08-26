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

    public Developer create(Developer developer) {
        return developerRepository.create(developer);
    }


    public void delete(Long id) {
        developerRepository.delete(id);
    }


    public Developer getById(Long id) {
        return developerRepository.getById(id);
    }


    public List<Developer> getAll() throws IOException {
        return developerRepository.getAll();
    }


    public void clearAll() {
        developerRepository.clearAll();
    }
}