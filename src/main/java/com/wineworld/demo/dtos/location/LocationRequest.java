package com.wineworld.demo.dtos.location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocationRequest {
    private float latitude;

    private float longitude;

    private String description;

    private String country;
}
