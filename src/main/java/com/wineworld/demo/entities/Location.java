package com.wineworld.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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

    @JsonIgnore
    @OneToMany(mappedBy = "location")
    private List<Product> products;

}
