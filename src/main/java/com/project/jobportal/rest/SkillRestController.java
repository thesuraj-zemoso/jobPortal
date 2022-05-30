package com.project.jobportal.rest;

import com.project.jobportal.entity.Job;
import com.project.jobportal.entity.Skill;
import com.project.jobportal.service.jobs.JobPortalService;
import com.project.jobportal.service.skills.SkillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class SkillRestController {
    private SkillService skillService;
    private JobPortalService jobPortalService;

    @Autowired
    public SkillRestController(SkillService skillService, JobPortalService jobPortalService) {
        this.skillService = skillService;
        this.jobPortalService = jobPortalService;
    }

    //    expose "/skills" and returns list of skills
    @GetMapping("/skills")
    public List<Skill> findAll(){return skillService.findALl();}

    //    add mapping for get /skills/{skillId}
    @GetMapping("/skills/{skillId}")
    public Skill getSkill(@PathVariable int skillId){
        Skill theSkill=skillService.findById(skillId);
        if(theSkill==null){
            throw new RuntimeException("Skill not found with id :"+skillId);
        }
        return theSkill;
    }

    //    add mapping for POST /skills/ - add new skill
    @PostMapping("/skills")
    public Skill addLocation(@RequestBody Skill theSkill){
        int jobId=theSkill.getJobId();
        Job theJob=jobPortalService.findById(jobId);
        if(theJob==null) throw new RuntimeException("Job not found with id :"+jobId);
        theSkill.setJobId(jobId);
        theSkill.setSkillId(0);
        theJob.addSkill(theSkill);
        skillService.save(theSkill);
        return theSkill;
    }

    //    add mapping for PUT /skills - updating an existing
    @PutMapping("/skills")
    public Skill updateLocation(@RequestBody Skill theSkill){
        Job theJob=jobPortalService.findById(theSkill.getJobId());
        if(theJob==null) throw new RuntimeException("Job not found with id :"+theSkill.getJobId());
        theJob.updateSkill(theSkill);
        skillService.save(theSkill);
        return theSkill;
    }

    //    add mapping for DELETE /skills
    @DeleteMapping("skills/{skillId}")
    public String deleteSkill(@PathVariable int skillId){
        Skill theSkill=skillService.findById(skillId);
        if(theSkill==null){
            throw new RuntimeException("Skill id not found "+skillId);
        }
        Job theJob=jobPortalService.findById(theSkill.getJobId());
        theJob.removeSkill(skillId);
        skillService.deleteById(skillId);
        return "Deleted Skill with id : "+skillId;
    }
}
