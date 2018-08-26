package com.alexander.model;

public class Skill extends General_Id{

    private String skill;

    public Skill(Long id, String skill) {
        super(id);
        this.skill = skill;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
