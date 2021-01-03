package com.wineworld.demo.dtos.product;

import java.io.File;

import org.springframework.web.multipart.MultipartFile;

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

    private MultipartFile picture;

    private String productDescription;

    private long genreId;

    private long locationId;

    private String producer;

    private float alcoholLevel;

    private int year;

    private float volume;
}
