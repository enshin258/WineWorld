package com.wineworld.demo.services;

import com.wineworld.demo.dtos.location.LocationRequest;
import com.wineworld.demo.dtos.location.LocationResponse;
import com.wineworld.demo.entities.Location;
import com.wineworld.demo.repositories.LocationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class LocationService {
    private final LocationRepository locationRepository;
    private ModelMapper modelMapper;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
        modelMapper = new ModelMapper();
    }


    public LocationResponse addLocation(LocationRequest locationRequest){
        Location location = modelMapper.map(locationRequest, Location.class);
        Location createdLocation = locationRepository.save(location);
        return modelMapper.map(createdLocation, LocationResponse.class);
    }

    public List<LocationResponse> getAllLocations(){
        return locationRepository.findAll().stream()
                .map(location -> modelMapper.map(location, LocationResponse.class))
                .collect(Collectors.toList());
    }

    public LocationResponse getLocationById(Long locationId){
        return modelMapper.map(locationRepository.findById(locationId).orElseThrow(EntityNotFoundException::new), LocationResponse.class);
    }

    public void deleteLocation(Long locationId){
        locationRepository.deleteById(locationId);
    }

    public LocationResponse updateLocation(Long locationId, LocationRequest locationRequest){
        Location locationToUpdate = locationRepository.getOne(locationId);
        locationToUpdate.setCountry(locationRequest.getCountry());
        locationToUpdate.setDescription(locationRequest.getDescription());
        locationToUpdate.setLatitude(locationRequest.getLatitude());
        locationToUpdate.setLongitude(locationRequest.getLongitude());
        locationRepository.save(locationToUpdate);
        return modelMapper.map(locationToUpdate, LocationResponse.class);
    }
}
