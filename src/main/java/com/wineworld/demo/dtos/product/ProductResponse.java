package com.wineworld.demo.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private long productId;

    private String name;

    private float price;

    private String pictureUrl;

    private long genreId;

    private String genreName;

    private long locationId;

    private float latitude;

    private float longitude;

    private String description;

    private String country;

    private String producer;

    private float alcoholLevel;

    private int year;

    private float volume;

}
