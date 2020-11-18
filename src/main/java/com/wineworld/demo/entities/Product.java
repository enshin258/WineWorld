package com.wineworld.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Products")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Product {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
 
    @NonNull
    private String name;
 
    @NonNull
    private float price;

    @NonNull
    private String pictureUrl;

    @NonNull
    private String genre;

    @NonNull
    @ManyToOne
    private Location location;

    @NonNull
    private String producer;

    @NonNull
    private float alcoholLevel;

    @NonNull
    private int year;

    @NonNull
    private float volume;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrderPosition> orderPositions;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Opinion> opinions;
 

}
