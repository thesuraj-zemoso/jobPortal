package com.project.jobportal.service.locations;

import com.project.jobportal.entity.Location;

import java.util.List;


public interface LocationService {
    public List<Location> findALl();
    public Location findById(int theId);
    public void save(Location theLocation);
    public String deleteById(int theId);
}
