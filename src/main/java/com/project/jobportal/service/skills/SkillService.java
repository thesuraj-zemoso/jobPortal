package com.project.jobportal.service.skills;

import com.project.jobportal.entity.Skill;

import java.util.List;


public interface SkillService {
    public List<Skill> findALl();
    public Skill findById(int theId);
    public void save(Skill theSkill);
    public String deleteById(int theId);
}
