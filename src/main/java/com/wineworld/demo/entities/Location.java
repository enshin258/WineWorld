package com.wineworld.demo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "location")
    private List<Product> products;

}
