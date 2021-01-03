package com.wineworld.demo.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String name;

    private float price;

    private File picture;

    private String productDescription;

    private long genreId;

    private long locationId;

    private String producer;

    private float alcoholLevel;

    private int year;

    private float volume;
}
