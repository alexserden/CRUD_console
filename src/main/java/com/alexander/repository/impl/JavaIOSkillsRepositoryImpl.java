package com.alexander.repository.impl;

import com.alexander.model.Skill;
import com.alexander.repository.SkillRepository;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

public class JavaIOSkillsRepositoryImpl implements SkillRepository {
    Path paths = Paths.get("src/main/resource/skills.txt");

    List<Skill> skills;
     BufferedReader bufferedReader;
     private Long countId = 0L;
    public JavaIOSkillsRepositoryImpl() throws IOException {
        skills = new ArrayList<>();
        bufferedReader = Files.newBufferedReader(paths);
    }

    @Override
    public Skill create(Skill skill) throws IOException {
        String text = "\n"+skill.getSkill();
        Files.write(paths,text.getBytes(),StandardOpenOption.APPEND);
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
            skills.add(new Skill(++countId,s));
        }
        return skills;
    }

    @Override
    public void clearAll() {

    }
}
