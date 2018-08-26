package com.alexander.view;

import com.alexander.controller.AccountController;
import com.alexander.controller.DeveloperController;
import com.alexander.controller.SkillsController;
import com.alexander.model.Developer;
import com.alexander.model.Skill;

import java.io.IOException;

import java.util.List;

public class DevelopersView {
    DeveloperController developerController = new DeveloperController();


    public DevelopersView() throws IOException {
    }

    public void show() throws IOException {
        List<Developer> list = developerController.getAll();
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("ID"+"  " + " NAME  " + "     Cpecialty  "+"       Account  " + "     Skills  ");
        for (Developer line : list) {
            String setSkill = "";
            for (Skill skill : line.getSkill()) {
                setSkill= setSkill+skill+".";
            }


            System.out.println(line.getId()+"    "+line.getName()+"    "+line.getSpecialty()+"     "+line.getAccount().getAccount()+"      "+setSkill);
        }
    }
}