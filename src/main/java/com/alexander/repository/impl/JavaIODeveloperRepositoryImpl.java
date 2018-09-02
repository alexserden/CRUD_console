package com.alexander.repository.impl;

import com.alexander.controller.AccountController;
import com.alexander.controller.SkillsController;
import com.alexander.model.Developer;
import com.alexander.model.Skill;
import com.alexander.repository.DeveloperRepository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JavaIODeveloperRepositoryImpl implements DeveloperRepository {
    AccountController accountRepository;
    SkillsController skillRepository;
    BufferedReader bufferedReader;
    Path paths = Paths.get("src/main/resource/developers.txt");
    private Long countId = 0L;

    public JavaIODeveloperRepositoryImpl() throws IOException {
        accountRepository = new AccountController();
        skillRepository = new SkillsController();
        List<Developer> list = getAll();
        for (Developer developer : list) {
            if (developer.getId() > countId) {
                countId = developer.getId();
            }
        }

    }

    @Override
    public void create(Developer developer) throws IOException {

        String text = "\n" + (++countId) + " " + developer.getName() + " " + developer.getSpecialty();
        Files.write(paths, text.getBytes(), StandardOpenOption.APPEND);
        accountRepository.create(developer.getAccount());
        String skill = "";
        for (Skill s : developer.getSkill()) {
            skill = s.getSkill() + skill;
        }
        skillRepository.create(new Skill(developer.getId(), skill));

    }
    @Override
    public void update(Developer developer) throws IOException {
         List<Developer> developersList = getAll();
        for (Developer d : developersList) {
            if(d.getId()==developer.getId()){
                d.setName(developer.getName());
                d.setSpecialty(developer.getSpecialty());
                accountRepository.update(developer.getAccount());
                skillRepository.update(developer.getSkill());
            }
        }
        BufferedWriter writer = Files.newBufferedWriter(paths);
            for (Developer dev : developersList) {
                writer.write(dev.getId()+" "+dev.getName()+" "+ dev.getSpecialty());
                writer.newLine();
            }
            writer.close();
      }

    @Override
    public void delete(Long id) throws IOException {
        List<Developer> newDevelopers = new ArrayList<>();
        List<Developer> developers = getAll();
        BufferedWriter writer = Files.newBufferedWriter(paths);

        try {

            for (Developer d : developers) {
                if (d.getId() != id) {
                    newDevelopers.add(d);
                }
            }
            for (Developer d : newDevelopers) {

                writer.write(d.getId() + " " + d.getName() + " " + d.getSpecialty()+"");
                writer.newLine();
            }
            writer.close();

            accountRepository.delete(id);
            skillRepository.delete(id);
        } catch (IOException e) {

        }
    }



    @Override
    public Developer getById(Long id) {
        return null;
    }

    @Override
    public List<Developer> getAll() throws IOException {
        List<Developer> developers = new ArrayList<>();
        bufferedReader = Files.newBufferedReader(paths);
        Long id;
        String name;
        String specialty;

        while (bufferedReader.ready()) {
            Set<Skill> skils = new HashSet<>();
            String s = bufferedReader.readLine();
            String[] temp = s.split(" ");
            id = Long.parseLong(temp[0]);
            name = temp[1];
            specialty = temp[2];
            Skill a = skillRepository.getById(id);
            String [] sk = a.getSkill().split(",");
            for (int i = 0; i <sk.length ; i++) {
                skils.add(a);
            }

            developers.add(new Developer(id, name, specialty, accountRepository.getById(id), skils));
        }
        bufferedReader.close();
        return developers;
    }

}
