package com.project.jobportal.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="job")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="job_id")
    private int jobId;

    @Column(name="title")
    private String title;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="job_id")
    private List<Location> locations;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="job_id")
    private List<Skill> skills;

    public Job() {
    }

    public Job(int jobId, String title, List<Location> locations, List<Skill> skills) {
        this.jobId = jobId;
        this.title = title;
        this.locations = locations;
        this.skills = skills;
    }

    public int getJobId() {
        return jobId;
    }

    public void setJobId(int jobId) {
        this.jobId = jobId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }

    public void addLocation(Location theLocation){
        locations.add(theLocation);
    }

    public void removeLocation(int locationId){
        for(Location location:locations){
            if(location.getLocationId()==locationId)
            {
                locations.remove(location);
                break;
            }
        }
    }

    public void updateLocation(Location theLocation) {
        for(Location location:locations){
            if(location.getLocationId()==theLocation.getLocationId())
            {
                location.setLocationName(theLocation.getLocationName());
                break;
            }
        }
    }


    public void addSkill(Skill theSkill){
        skills.add(theSkill);
    }

    public void removeSkill(int skillId){
        for(Skill skill:skills){
            if(skill.getSkillId()==skillId)
            {
                skills.remove(skill);
                break;
            }
        }
    }

    public void updateSkill(Skill theSkill) {
        for(Skill skill:skills){
            if(skill.getSkillId()==theSkill.getSkillId())
            {
                skill.setSkillName(theSkill.getSkillName());
                break;
            }
        }
    }
}
