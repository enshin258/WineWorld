package com.wineworld.demo.dtos.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String date;

    private String address;

    private String city;

    private String postalCode;

    private float totalCost;

    private long userId;

}
