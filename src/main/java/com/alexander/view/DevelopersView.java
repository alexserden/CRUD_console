package com.alexander.view;

import com.alexander.controller.DeveloperController;
import com.alexander.model.Account;
import com.alexander.model.Developer;
import com.alexander.model.Skill;

import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DevelopersView {
    DeveloperController developerController = new DeveloperController();
    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    Long id = 0L;
    String name;
    String specialty;
    Account account;
    Set<Skill>skills =  new HashSet<Skill>();

    public DevelopersView() throws IOException {
    }

    public void addDeveloper() throws IOException {


        System.out.println("Введите name разработчика");
        try {
            name = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("неправильный ввод");
        }
        System.out.println("Введите specialty разработчика");
        try {
            specialty = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("неправильный ввод");
        }
        System.out.println("Введите account разработчика");
        try {
            account = new Account(id,bufferedReader.readLine());
        } catch (IOException e) {
            System.out.println("неправильный ввод");
        }
        System.out.println("Введите skills разработчика через запятую");

        skills.add(new Skill(id,bufferedReader.readLine()));

       developerController.create(new Developer(id,name,specialty,account,skills));
    }

    public void updateDeveloper() throws IOException {
        Long id = 0L;
        System.out.println("Введите Id разработчика, которого нужно редактировать ");
        try {
           id = Long.parseLong(bufferedReader.readLine());
        } catch (IOException e) {
            System.out.println("неправильный ввод");
        }
        System.out.println("Введите name разработчика");
        try {
            name = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("неправильный ввод");
        }
        System.out.println("Введите specialty разработчика");
        try {
            specialty = bufferedReader.readLine();
        } catch (IOException e) {
            System.out.println("неправильный ввод");
        }
        System.out.println("Введите account разработчика");
        try {
            account = new Account(id,bufferedReader.readLine());
        } catch (IOException e) {
            System.out.println("неправильный ввод");
        }
        System.out.println("Введите skills разработчика через запятую");

        skills.add(new Skill(id,bufferedReader.readLine()));

        developerController.update(new Developer(id,name,specialty,account,skills));
    }
    public void showAll() throws IOException {

        List<Developer> developerList = developerController.getAll();

        System.out.println("--------------------------------------------------------------------");
        System.out.println("ID    NAME       Cpecialty              Account         Skills  ");
        System.out.println("--------------------------------------------------------------------");

        for (Developer developer : developerList) {
            System.out.printf("%-6s", developer.getId());
            System.out.printf("%-11s", developer.getName());
            System.out.printf("%-23s", developer.getSpecialty());
            System.out.printf("%-16s",developer.getAccount().getAccount());
            for (Skill skill : developer.getSkill()) {
                System.out.print(skill.getSkill()+" ");
                }
            System.out.println();
            System.out.println("--------------------------------------------------------------------");
            }
    }
    public void delete() throws IOException {
        System.out.println("Введите ID пользователя которого хотите удалить");
        developerController.delete(Long.valueOf(bufferedReader.readLine()));
    }
}