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

public class JavaIOSkillsRepositoryImpl implements SkillRepository {
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

        String text = "\n" + (++countId) + " " + skill.getSkill();
        Files.write(paths, text.getBytes(), StandardOpenOption.APPEND);
    }


    @Override
    public void update(Skill skill) throws IOException {

    }


    @Override
    public void update(Set<Skill> skill) throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader bufferedReader = Files.newBufferedReader(paths);


        while (bufferedReader.ready()) {
            list.add(bufferedReader.readLine());
        }
        bufferedReader.close();
        BufferedWriter writer = Files.newBufferedWriter(paths);
        for(String s : list){
            for(Skill sk : skill){
                if(Long.parseLong(s.split(" ")[0])==sk.getId()){
                    writer.write(sk.getId()+" "+sk.getSkill());
                    writer.newLine();
                }
            }
            writer.write(s.split(" ")[0]+" " +s.split(" ")[1]);
            writer.newLine();
        }
        writer.close();

    }

    @Override
    public void delete(Long id) throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader bufferedReader = Files.newBufferedReader(paths);

        while (bufferedReader.ready()) {
            list.add(bufferedReader.readLine());
        }

        bufferedReader.close();
        BufferedWriter writer = Files.newBufferedWriter(paths);
        for (String s : list) {
            if (Long.parseLong(s.split(" ")[0]) != id) {
                writer.write(s.split(" ")[0] + " " + s.split(" ")[1]);
            }
        }

        writer.close();
    }


    @Override
    public Skill getById(Long id) throws IOException {
        BufferedReader bufferedReader = Files.newBufferedReader(paths);
        List<String> list = new ArrayList<>();
        while (bufferedReader.ready()) {
            list.add(bufferedReader.readLine());
        }
        bufferedReader.close();
        for (String s : list) {
            if (Long.parseLong(s.split(" ")[0]) == id) {
                return new Skill(id, s.split(" ")[1]);
            }
        }


        return null;
    }

    @Override
    public List<Skill> getAll() throws IOException {
        List<Skill> skills = new ArrayList<>();
        BufferedReader bufferedReader = Files.newBufferedReader(paths);


        while (bufferedReader.ready()) {
            String[] sk = bufferedReader.readLine().split(" ");
            skills.add(new Skill(Long.parseLong(sk[0]), sk[1]));
        }
        bufferedReader.close();
        return skills;

    }

    private Skill getByName(Skill name) throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader reader = Files.newBufferedReader(paths);
        while (reader.ready()) {
            list.add(reader.readLine());
        }
        reader.close();
        for (String data : list) {
            if (data.split(" ")[1].equals(name.getSkill())) {
                return new Skill(Long.parseLong(data.split(" ")[0]), name.getSkill());
            }
        }
        return null;
    }


}
