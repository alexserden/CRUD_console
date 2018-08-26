package com.alexander.model;

import java.util.Set;

public class Developer extends General_Id {


    private String name;
    private String specialty;
    private Set<Skill> skill;
    private Account account;


    public Developer(Long id, String name, String specialty, Set<Skill> skill, Account account, Set<Skill> skils) {
        super(id);
        this.name = name;
        this.specialty = specialty;
        this.skill = skill;
        this.account = account;

    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Set<Skill> getSkill() {
        return skill;
    }

    public void setSkill(Set<Skill> skill) {
        this.skill = skill;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


}
