package com.project.jobportal.rest;

import com.project.jobportal.entity.Job;
import com.project.jobportal.service.jobs.JobPortalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class JobPortalRestController {
    @Autowired
    private JobPortalService jobPortalService;

    public JobPortalRestController(JobPortalService jobPortalService) {
        this.jobPortalService = jobPortalService;
    }

//    expose "/jobs" and return list of jobs
    @GetMapping("/jobs")
    public List<Job> findAll(){
        return jobPortalService.findALl();
    }

//    add mapping for get /jobs/{jobId}
    @GetMapping("/jobs/{jobId}")
    public Job getJob(@PathVariable int jobId){
        Job theJob= jobPortalService.findById(jobId);
        if(theJob==null){
            throw new RuntimeException("Job not found with id :"+jobId);
        }
        return theJob;
    }

//    add mapping for POST /jobs - add new jobItem
    @PostMapping("/jobs")
    public Job addJob(@RequestBody Job theJob){
        theJob.setJobId(0);
        jobPortalService.save(theJob);
        return theJob;
    }

//    add mapping for PUT /jobs - updating an existing jobItem
    @PutMapping("/jobs")
    public Job updateJob(@RequestBody Job theJob){
        jobPortalService.save(theJob);
        return theJob;
    }

//    add mapping for DELETE /list/{jobId} -delete jobItem
    @DeleteMapping("/jobs/{jobId}")
    public String deleteJob(@PathVariable int jobId){
        Job theJob= jobPortalService.findById(jobId);
        if(theJob==null){
            throw new RuntimeException("Job id not found "+jobId);
        }
        jobPortalService.deleteById(jobId);
        return "Deleted Job with id : "+jobId;
    }
}
