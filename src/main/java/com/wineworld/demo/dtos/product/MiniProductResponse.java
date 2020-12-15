package com.wineworld.demo.dtos.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MiniProductResponse {
    private long productId;

    private String name;

    private float price;

    private String productDescription;

    private String pictureUrl;
}
