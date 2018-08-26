package com.alexander.repository.impl;

import com.alexander.model.Account;
import com.alexander.model.Developer;
import com.alexander.model.Skill;
import com.alexander.repository.AccountRepository;
import com.alexander.repository.DeveloperRepository;
import com.alexander.repository.SkillRepository;

import java.io.BufferedReader;
import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JavaIODeveloperRepositoryImpl implements DeveloperRepository {
    AccountRepository accountRepository;
    SkillRepository skillRepository;
    BufferedReader bufferedReader;
    List<Developer> developers;
    private Long countId = 0L;
    public JavaIODeveloperRepositoryImpl() throws IOException {
         accountRepository = new JavaIOAccountRepositoryImpl();
         skillRepository = new JavaIOSkillsRepositoryImpl();
        bufferedReader  = Files.newBufferedReader(Paths.get("src/main/resource/developers.txt"));
        developers = new ArrayList<>();
    }

    @Override
    public Developer create(Developer developer) {
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
        Set<Skill>  skils = new HashSet<>();

        while(bufferedReader.ready()){
            String s = bufferedReader.readLine();
            String [] temp = s.split(" ");
             id = Long.valueOf(temp[0]);
             name = temp[1];
             specialty = temp[2];
//              String setSkill = skillRepository.getById(id).getSkill();
//              String [] mas =  setSkill.split(",");
//            for (int i = 0; i <mas.length ; i++) {
//
//                skils.add(new Skill(id,mas[i]));
//            }

             developers.add(new Developer(id,name,specialty,skils,accountRepository.getById(id),skils));
             }






        return developers;
        }


    @Override
    public void clearAll() {

    }
}
