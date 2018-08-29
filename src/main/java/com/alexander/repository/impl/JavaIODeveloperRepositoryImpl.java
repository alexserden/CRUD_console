package com.alexander.repository.impl;

import com.alexander.model.Developer;
import com.alexander.model.Skill;
import com.alexander.repository.AccountRepository;
import com.alexander.repository.DeveloperRepository;
import com.alexander.repository.SkillRepository;

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
    AccountRepository accountRepository;
    SkillRepository skillRepository;
    BufferedReader bufferedReader;
    List<Developer> developers;
    Path paths = Paths.get("src/main/resource/developers.txt");

    private Long countId = 0L;
    public JavaIODeveloperRepositoryImpl() throws IOException {
         accountRepository = new JavaIOAccountRepositoryImpl();
         skillRepository = new JavaIOSkillsRepositoryImpl();
        bufferedReader  = Files.newBufferedReader(paths);

        developers = new ArrayList<>();
    }

    @Override
    public Developer create(Developer developer) throws IOException {

        String text = "\n"+developer.getId()+" "+developer.getName()+" "+developer.getSpecialty();
        Files.write(paths,text.getBytes(),StandardOpenOption.APPEND);
        accountRepository.create(developer.getAccount());
        String skill = "";
        for (Skill s : developer.getSkill()) {
            skill = s.getSkill() + skill;
        }
        skillRepository.create(new Skill(developer.getId(),skill));
return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Developer getById(Long id) {
        return null;
    }

    @Override
    public List<Developer> getAll() throws IOException {
        Long id;
        String name;
        String specialty;

        while(bufferedReader.ready()){
            Set<Skill>  skils = new HashSet<>();
            String s = bufferedReader.readLine();
            String [] temp = s.split(" ");
             id = Long.valueOf(temp[0]);
             name = temp[1];
             specialty = temp[2];
           Skill a = skillRepository.getById(id);
           skils.add(a);
           developers.add(new Developer(id,name,specialty,accountRepository.getById(id),skils));
             }






        return developers;
        }


    @Override
    public void clearAll() throws IOException {
        BufferedWriter bufferedWriter = Files.newBufferedWriter(paths);
        bufferedWriter.write("");
        bufferedWriter.close();
    }
}
