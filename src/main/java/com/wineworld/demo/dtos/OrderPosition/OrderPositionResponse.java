package com.wineworld.demo.dtos.OrderPosition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPositionResponse {
    private long orderPositionId;

    private long orderId;

    private long productId;

    private String name;

    private float price;

    private String pictureUrl;

    private String producer;

    private String genre;

    private float alcoholLevel;

    private int year;

    private float volume;

}
