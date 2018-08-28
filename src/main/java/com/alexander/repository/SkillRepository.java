package com.alexander.repository;

import com.alexander.model.Skill;

import java.io.IOException;
import java.util.List;


public interface SkillRepository extends GenericRepository<Skill,Long> {
    Skill create(Skill t) throws IOException;

    void delete(Long id);

    Skill getById(Long id) throws IOException;

    List<Skill> getAll() throws IOException;

    void clearAll();
}
