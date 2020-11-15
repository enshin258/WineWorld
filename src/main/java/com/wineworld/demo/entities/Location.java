package com.wineworld.demo.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Locations")
@RequiredArgsConstructor
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long locationId;

    @NonNull
    private float latitude;

    @NonNull
    private float longitude;

    @NonNull
    private String description;

    @NonNull
    private String country;

}