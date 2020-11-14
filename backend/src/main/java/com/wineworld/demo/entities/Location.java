package com.wineworld.demo.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Location {
 
    @NotNull(message = "Latitude is required.")
    private float latitude;

    @NotNull(message = "Longitude is required.")
    private float longitude;

    @NotNull(message = "Description is required.")
    private String description;

    @NotNull(message = "Country is required.")
    private String country;
 

 
    // all arguments contructor 
    // standard getters and setters
}