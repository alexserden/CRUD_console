package com.alexander.model;

public class Skill extends IdEntity {

    private String name;

    public Skill(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getSkill() {
        return name;
    }

    public void setSkill(String name) {
        this.name = name;
    }
}
