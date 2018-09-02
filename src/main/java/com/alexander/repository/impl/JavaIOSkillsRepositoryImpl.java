package com.alexander.repository.impl;

import com.alexander.model.Skill;
import com.alexander.repository.SkillRepository;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public  class JavaIOSkillsRepositoryImpl implements SkillRepository {
    Path paths = Paths.get("src/main/resource/skills.txt");

    private static Long countId = 0L;

    public JavaIOSkillsRepositoryImpl() throws IOException {
        try {
            List<Skill> addId = getAll();

            for (Skill skill : addId) {
                if (skill.getId() > countId) {
                    countId = skill.getId();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void create(Skill skill) throws IOException {

        String text = "\n" +(++countId)+ " " + skill.getSkill();
        Files.write(paths, text.getBytes(), StandardOpenOption.APPEND);
    }



    @Override
    public void update(Skill skill) throws IOException {

    }


    @Override
   public void update(Set<Skill> skill) throws IOException
    {
        List<Skill> skills = getAll();
        for(Skill sk : skill) {
            for (Skill acc : skills) {
                if (sk.getId().equals(acc.getId())) {
                    acc.setSkill(sk.getSkill());

                }
            }
        }
        BufferedWriter writer = Files.newBufferedWriter(paths);
        for (Skill s : skills) {
            writer.write(s.getId()+" "+s.getSkill());
            writer.newLine();
        }
        writer.close();
    }

    @Override
    public void delete(Long id) throws IOException {
        List<Skill> newSkills = new ArrayList<>();
        List<Skill> skills = getAll();
        BufferedWriter writer = Files.newBufferedWriter(paths);



        for (Skill s : skills) {
            if (s.getId() != id) {
                newSkills.add(s);
            }
        }
        for(Skill s : newSkills){
            writer.write(s.getId()+" "+s.getSkill());
            writer.newLine();
        }
        writer.close();
        }


    @Override
    public Skill getById(Long id) throws IOException {
        List<Skill> sk = getAll();

        for (Skill s : sk) {
            if (s.getId() == id) return s;
        }
        return null;
    }

    @Override
    public List<Skill> getAll() throws IOException {
        List<Skill> skills = new ArrayList<>();
        BufferedReader bufferedReader = Files.newBufferedReader(paths);


        while (bufferedReader.ready()) {
            String[] sk = bufferedReader.readLine().split(" ");
            skills.add(new Skill(Long.parseLong(sk[0]),sk[1]));
        }
        bufferedReader.close();
        return skills;

    }
    private Skill getByName(Skill name) throws IOException {
        List<Skill> data = getAll();

        for (Skill skill : data) {
            if (skill.getSkill().equals(name)) {
                return skill;
            }
        }
        return null;
    }


}
