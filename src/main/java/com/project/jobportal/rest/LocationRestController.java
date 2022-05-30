package com.project.jobportal.rest;

import java.util.List;

import com.project.jobportal.entity.Job;
import com.project.jobportal.entity.Location;
import com.project.jobportal.service.jobs.JobPortalService;
import com.project.jobportal.service.locations.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class LocationRestController {
    private LocationService locationService;
    private JobPortalService jobPortalService;

    @Autowired
    public LocationRestController(LocationService locationService, JobPortalService jobPortalService) {
        this.locationService = locationService;
        this.jobPortalService = jobPortalService;
    }

//    expose "/locations" and returns list of locations
    @GetMapping("/locations")
    public List<Location> findAll(){return locationService.findALl();}

//    add mapping for get /locations/{locationId}
    @GetMapping("/locations/{locationId}")
    public Location getLocation(@PathVariable int locationId){
        Location theLocation=locationService.findById(locationId);
        if(theLocation==null){
            throw new RuntimeException("Location not found with id :"+locationId);
        }
        return theLocation;
    }

//    add mapping for POST /jobs - add new location
    @PostMapping("/locations")
    public Location addLocation(@RequestBody Location theLocation){
        int jobId=theLocation.getJobId();
        Job theJob=jobPortalService.findById(jobId);
        if(theJob==null) throw new RuntimeException("Job not found with id :"+jobId);
        theLocation.setJobId(jobId);
        theLocation.setLocationId(0);
        theJob.addLocation(theLocation);
        locationService.save(theLocation);
        return theLocation;
    }

//    add mapping for PUT /location - updating an existing
    @PutMapping("/locations")
    public Location updateLocation(@RequestBody Location theLocation){
        Job theJob=jobPortalService.findById(theLocation.getJobId());
        if(theJob==null) throw new RuntimeException("Job not found with id :"+theLocation.getJobId());
        theJob.updateLocation(theLocation);
        locationService.save(theLocation);
        return theLocation;
    }

//    add mapping for DELETE /locations
    @DeleteMapping("locations/{locationId}")
    public String deleteLocation(@PathVariable int locationId){
        Location theLocation=locationService.findById(locationId);
        if(theLocation==null){
            throw new RuntimeException("Location id not found "+locationId);
        }
        Job theJob=jobPortalService.findById(theLocation.getJobId());
        theJob.removeSkill(locationId);
        locationService.deleteById(locationId);
        return "Deleted Location with id : "+locationId;
    }
}
