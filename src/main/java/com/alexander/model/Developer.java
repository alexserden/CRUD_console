package com.alexander.model;

import java.util.Set;

public class Developer extends IdEntity {


    private String name;
    private String specialty;
    private Account account;
    private Set<Skill> skill;


    public Developer(Long id, String name, String specialty, Account account, Set<Skill> skill) {
        super(id);
        this.name = name;
        this.specialty = specialty;
        this.account = account;
        this.skill = skill;

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
