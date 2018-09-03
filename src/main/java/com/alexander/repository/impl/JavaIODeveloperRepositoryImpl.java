package com.alexander.repository.impl;

import com.alexander.controller.AccountController;
import com.alexander.controller.SkillsController;
import com.alexander.model.Account;
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

        String text = "\n" + (++countId) + " " + developer.getName() + " " + developer.getSpecialty();              // создаем text который будем записывать в файл
        Files.write(paths, text.getBytes(), StandardOpenOption.APPEND);                                             // переобразовываем text в байты и записываем в конец файла developers
        accountRepository.create(developer.getAccount());                                                           // вызываем методы create для остальных классов JavaIOAccountsRepositoryImpl, JavaIOSkillsRepositoryImpl
        String skill = "";
        for (Skill s : developer.getSkill()) {
            skill = s.getSkill() + skill;
        }
        skillRepository.create(new Skill(developer.getId(), skill));

    }

    @Override
    public void update(Developer developer) throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader bufferedReader = Files.newBufferedReader(paths);


        while (bufferedReader.ready()) {
            list.add(bufferedReader.readLine());
        }
        bufferedReader.close();
        BufferedWriter writer = Files.newBufferedWriter(paths);
        for (String s : list) {
            if (Long.parseLong(s.split(" ")[0]) == developer.getId()) {
                writer.write(developer.getId() + " " + developer.getName() + " " + developer.getSpecialty());
                writer.newLine();
            }else{
                writer.write(s.split(" ")[0] +" "+s.split(" ")[1]+" "+ s.split(" ")[2]);
                writer.newLine();
            }
        }
        writer.close();
    }

    @Override
    public void delete(Long id) throws IOException {
        List<String> list = new ArrayList<>();
        BufferedReader bufferedReader = Files.newBufferedReader(paths);
        BufferedWriter writer = Files.newBufferedWriter(paths);
        while (bufferedReader.ready()) {
            list.add(bufferedReader.readLine());
        }
        for (String d : list) {
            if (Long.parseLong(d.split(" ")[0]) != id) {
                writer.write(d.split(" ")[0] + " " + d.split(" ")[1] + " " + d.split(" ")[2] + "");
                writer.newLine();
            }
        }
        bufferedReader.close();
        writer.close();
        accountRepository.delete(id);
        skillRepository.delete(id);

    }


    @Override
    public Developer getById(Long id) throws IOException {
        BufferedReader bufferedReader = Files.newBufferedReader(paths);
        List<String> list = new ArrayList<>();
        while (bufferedReader.ready()) {
            list.add(bufferedReader.readLine());
        }
        bufferedReader.close();

        for (String s : list) {
            if (Long.parseLong(s.split(" ")[0]) == id) {
                Account account = accountRepository.getById(id);
                Skill skill = skillRepository.getById(id);
                Set<Skill> sk = new HashSet<>();
                sk.add(skill);
                return new Developer(id, s.split(" ")[1],
                        s.split(" ")[2], account, sk);
            }
        }


        return null;
    }

    @Override
    public List<Developer> getAll() throws IOException {
        List<Developer> developers = new ArrayList<>();
        BufferedReader bufferedReader = Files.newBufferedReader(paths);
        Long id;
        String name;
        String specialty;

        while (bufferedReader.ready()) {
            Set<Skill> skils = new HashSet<>();
            String s = bufferedReader.readLine();  //читаем с файла построчно
            String[] temp = s.split(" ");   //разбиваем строку с помощью регулярки
            id = Long.parseLong(temp[0]);
            name = temp[1];
            specialty = temp[2];
            Account account = accountRepository.getById(id);    //// по id находим соответствующий Account разработчика
            Skill a = skillRepository.getById(id);            // по id находим соответствующие Skills разработчика
            String[] sk = a.getSkill().split(",");     // рабиваем его с помощью регулярки
            for (int i = 0; i < sk.length; i++) {              // и добавляем в HashSet
                skils.add(new Skill(id, sk[i]));
            }
            developers.add(new Developer(id, name, specialty, account, skils));    //добавляем в список developers
        }                                                                          // созданного разработчика
        bufferedReader.close();                                                    // закрываем поток для чтения
        return developers;
    }

}
