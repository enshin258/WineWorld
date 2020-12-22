package com.wineworld.demo.controllers;

import com.wineworld.demo.dtos.location.LocationRequest;
import com.wineworld.demo.dtos.location.LocationResponse;
import com.wineworld.demo.services.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/locations")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/add")
    public ResponseEntity<LocationResponse> addLocation(@RequestBody LocationRequest locationRequest){
        if(locationRequest != null){
            LocationResponse locationResponse = locationService.addLocation(locationRequest);
            return new ResponseEntity<>(locationResponse, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<LocationResponse>> getAllLocations(){
        return new ResponseEntity<>(locationService.getAllLocations(), HttpStatus.OK);
    }

    @GetMapping("/get/{locationId}")
    public ResponseEntity<LocationResponse> getLocation(@PathVariable Long locationId){
        return new ResponseEntity<>(locationService.getLocationById(locationId), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{locationId}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long locationId){
        locationService.deleteLocation(locationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/update/{locationId}")
    public ResponseEntity<LocationResponse> updateLocation(@PathVariable Long locationId, @RequestBody LocationRequest locationRequest){
        return new ResponseEntity<>(locationService.updateLocation(locationId, locationRequest), HttpStatus.OK);
    }
}
