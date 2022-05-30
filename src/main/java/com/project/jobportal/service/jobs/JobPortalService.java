package com.project.jobportal.service.jobs;



import com.project.jobportal.entity.Job;

import java.util.List;


public interface JobPortalService {
    public List<Job> findALl();
    public Job findById(int theId);
    public void save(Job theJob);
    public String deleteById(int theId);
}
