package com.project.jobportal.service.locations;

import com.project.jobportal.dao.LocationRepository;
import com.project.jobportal.dao.SkillRepository;
import com.project.jobportal.entity.Job;
import com.project.jobportal.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LocationServiceImpl implements LocationService{
    private LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> findALl() {
        return locationRepository.findAll();
    }

    @Override
    public Location findById(int theId) {
        Optional<Location> result= locationRepository.findById(theId);
        Location theLocation=null;
        if(result.isPresent()) theLocation=result.get();
        else {
//            we didn't find the employee
            throw new RuntimeException("Did not found Location id : "+theId);
        }
        return theLocation;
    }

    @Override
    public void save(Location theLocation) {
        locationRepository.save(theLocation);
    }

    @Override
    public String deleteById(int theId) {
        locationRepository.deleteById(theId);
        return "Deleted Location With Id : " + theId;
    }
}
