package com.wineworld.demo.dtos.Order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private long orderId;

    private String date;

    private String address;

    private String city;

    private String postalCode;

    private float totalCost;

    private long userId;

    private String login;

    private String email;

}
