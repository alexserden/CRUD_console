package com.alexander.controller;

import com.alexander.model.Skill;
import com.alexander.repository.SkillRepository;
import com.alexander.repository.impl.JavaIOSkillsRepositoryImpl;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public class SkillsController {
    SkillRepository skillRepository = new JavaIOSkillsRepositoryImpl();

    public SkillsController() throws IOException {
    }

    public void create(Skill skill) throws IOException {
         skillRepository.create(skill);
    }

    public void update(Set<Skill> skill) throws IOException {

        skillRepository.update(skill);

    }
    public void delete(Long id) throws IOException {
        skillRepository.delete(id);
    }


    public Skill getById(Long id) throws IOException {
        return skillRepository.getById(id);
    }


    public List<Skill> getAll() throws IOException {
        return skillRepository.getAll();
    }


}
