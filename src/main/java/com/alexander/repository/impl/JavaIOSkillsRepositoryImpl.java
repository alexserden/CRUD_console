package com.alexander.repository.impl;

import com.alexander.model.Skill;
import com.alexander.repository.SkillRepository;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JavaIOSkillsRepositoryImpl implements SkillRepository {
     List<Skill> skills;
     BufferedReader bufferedReader;
     private Long countId = 0L;
    public JavaIOSkillsRepositoryImpl() throws IOException {
        skills = new ArrayList<>();
        bufferedReader = Files.newBufferedReader(Paths.get("src/main/resource/skills.txt"));
    }

    @Override
    public Skill create(Skill skill) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Skill getById(Long id) throws IOException {

        for (Skill s :  getAll()) {
            if(s.getId()==id) return s;
        }
        return null;
    }

    @Override
    public List<Skill> getAll() throws IOException {
        while(bufferedReader.ready()){
              String s = bufferedReader.readLine();
            skills.add(new Skill(countId++,s));
        }
        return skills;
    }

    @Override
    public void clearAll() {

    }
}
