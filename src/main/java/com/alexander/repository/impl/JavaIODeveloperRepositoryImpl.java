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
         List<Developer> developersList = getAll();                                             // получаем все разработчиков
        for (Developer d : developersList) {                                                    // проходимся по списку
            if(d.getId()==developer.getId()){                                                   // если есть совпадающие айди
                d.setName(developer.getName());                                                 // с помощью метода set меняем значения
                d.setSpecialty(developer.getSpecialty());                                       // вызываем метод  update для класса JavaIOAccountsRepositoryImpl
                accountRepository.update(developer.getAccount());                               // вызываем метод  update для класса JavaIOSkillsRepositoryImpl
                skillRepository.update(developer.getSkill());
            }
        }
        BufferedWriter writer = Files.newBufferedWriter(paths);                                     // создаем поток для записи в файл
            for (Developer dev : developersList) {                                                  // проходимся по списку
                writer.write(dev.getId()+" "+dev.getName()+" "+ dev.getSpecialty());            // записываем данные в файл developers
                writer.newLine();                                                                   // переходим на следующую строчку в файле
            }
            writer.close();                                                                         // закрываем поток во избежания утечки информации
      }

    @Override
    public void delete(Long id) throws IOException {                    // удаление по id
        List<Developer> newDevelopers = new ArrayList<>();              // создаем два списка
        List<Developer> developers = getAll();                          //  в список  developers заносим всех разработчиков
        BufferedWriter writer = Files.newBufferedWriter(paths);         // создаем поток для записи в файл
        try {

            for (Developer d : developers) {                            // проходимся по списку разработчиков и при не совпадении
                if (d.getId() != id) {                                  // айди доваляем их в новый список если айди совпадают
                    newDevelopers.add(d);                               //соответственно разработчик не попадает в новый список и теряется
                }
            }
            for (Developer d : newDevelopers) {                             //  проходимся по новому списку разработчиков

                writer.write(d.getId() + " " + d.getName() + " " + d.getSpecialty()+"");   // пишем в файл developers первые три значения остальные
                writer.newLine();                                                               // переходим на новую строку
            }
            writer.close();                                                                     // закрываем файл воизбежания утечки информации

            accountRepository.delete(id);                                                       // вызываем метод  delete для класса JavaIOAccountRepositoryImpl
            skillRepository.delete(id);                                                         // вызываем метод  delete для класса JavaIOSkillsRepositoryImpl
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public Developer getById(Long id) throws IOException {
        List<Developer> developers = new ArrayList<>(getAll());         // создаем список и добавляем в него всех разработчиков
        for (Developer d : developers) {                                // проходимся по списку сравнивая id
            if (d.getId() == id) return d;                              // при совпадении возвращаем разработчика
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
            String [] sk = a.getSkill().split(",");     // рабиваем его с помощью регулярки
            for (int i = 0; i <sk.length ; i++) {              // и добавляем в HashSet
                skils.add(new Skill(id,sk[i]));
            }
            developers.add(new Developer(id, name, specialty, account, skils));    //добавляем в список developers
        }                                                                          // созданного разработчика
        bufferedReader.close();                                                    // закрываем поток для чтения
        return developers;
    }

}
