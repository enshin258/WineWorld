package com.wineworld.demo.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long productId;
 
    @NotNull(message = "Product name is required.")
    private String name;
 
    @NotNull(message = "Price is required.")
    private float price;
 
    private String pictureUrl;

    @NotNull(message = "Genre is required.")
    private String genre;

    private Location location;

    @NotNull(message = "Producer is required.")
    private String producer;

    @NotNull(message = "Alcohol level is required.")
    private float alcoholLevel;

    @NotNull(message = "Year is required.")
    private int year;

    @NotNull(message = "Volume is required.")
    private float volume;
 
    // all arguments contructor 
    // standard getters and setters
}