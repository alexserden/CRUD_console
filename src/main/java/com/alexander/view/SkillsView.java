package com.alexander.view;


import com.alexander.controller.SkillsController;
import com.alexander.model.Skill;

import java.io.IOException;
import java.util.List;

public class SkillsView {
    SkillsController skillsController = new SkillsController();

    public SkillsView() throws IOException {

    }

    public void showSkills() throws IOException {
        List<Skill> skillList = skillsController.getAll();
        System.out.println("------------skills------------");
        for (Skill skill : skillList) {
                System.out.printf("%-16s%n",skill.getSkill());

            }

        }
    }

