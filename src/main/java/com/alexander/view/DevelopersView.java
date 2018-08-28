package com.alexander.view;

import com.alexander.controller.AccountController;
import com.alexander.controller.DeveloperController;
import com.alexander.controller.SkillsController;
import com.alexander.model.Account;
import com.alexander.model.Developer;
import com.alexander.model.Skill;

import java.io.IOException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DevelopersView {
    DeveloperController developerController = new DeveloperController();


    public DevelopersView() throws IOException {
    }

    public void show() throws IOException {

        List<Developer> list = developerController.getAll();

        System.out.println("---------------------------------------------------------------");
        System.out.println("ID    NAME        Cpecialty              Account         Skills  ");

        Set<Skill> sl = new HashSet<>();
        sl.add(new Skill(2L,"Pascal"));
        developerController.create(new Developer(2l,"Vova","PhpDeveloper",new Account(2L,"vovan@"),sl));
        for (Developer dev : list) {


            System.out.printf("%-6s", dev.getId());
            System.out.printf("%-11s", dev.getName());
            System.out.printf("%-24s", dev.getSpecialty());
            System.out.printf("%-19s", dev.getAccount().getAccount());
            for (Skill skill : dev.getSkill()) {
                System.out.print(skill.getSkill() + " ");

            }

            System.out.println();

        }

    }
}