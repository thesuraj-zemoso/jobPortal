package com.project.jobportal.service.skills;

import com.project.jobportal.dao.SkillRepository;
import com.project.jobportal.entity.Job;
import com.project.jobportal.entity.Skill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SkillServiceImpl implements SkillService{
    private SkillRepository skillRepository;

    @Autowired
    public SkillServiceImpl(SkillRepository skillRepository) {
        this.skillRepository = skillRepository;
    }

    @Override
    public List<Skill> findALl() {
        return skillRepository.findAll();
    }

    @Override
    public Skill findById(int theId) {
        Optional<Skill> result= skillRepository.findById(theId);
        Skill theSkill=null;
        if(result.isPresent()) theSkill=result.get();
        else {
//            we didn't find the employee
            throw new RuntimeException("Did not found Skill id : "+theId);
        }
        return theSkill;
    }

    @Override
    public void save(Skill theSkill) {
        skillRepository.save(theSkill);
    }

    @Override
    public String deleteById(int theId) {
        skillRepository.deleteById(theId);
        return "Deleted Skill With Id : " + theId;
    }
}
