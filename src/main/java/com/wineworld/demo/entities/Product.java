package com.wineworld.demo.entities;

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
    private String productDescription;

    @NonNull
    @ManyToOne
    private Genre genre;

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

    @OneToMany(mappedBy = "product")
    private List<OrderPosition> orderPositions;

    @OneToMany(mappedBy = "product")
    private List<Opinion> opinions;
 

}
