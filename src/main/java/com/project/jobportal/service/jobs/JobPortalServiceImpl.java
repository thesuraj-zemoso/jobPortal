package com.project.jobportal.service.jobs;

import com.project.jobportal.dao.JobRepository;
import com.project.jobportal.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JobPortalServiceImpl implements JobPortalService {
    private JobRepository jobRepository;

    @Autowired
    public JobPortalServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public List<Job> findALl() {
        return jobRepository.findAll();
    }

    @Override
    public Job findById(int theId) {
        Optional<Job> result= jobRepository.findById(theId);
        Job theJob=null;
        if(result.isPresent()) theJob=result.get();
        else {
//            we didn't find the employee
            throw new RuntimeException("Did not found Job id : "+theId);
        }
        return theJob;
    }

    @Override
    public void save(Job theJob) {
        jobRepository.save(theJob);
    }

    @Override
    public String deleteById(int theId) {
        jobRepository.deleteById(theId);
        return "Deleted Job With Id : " + theId;
    }
}
