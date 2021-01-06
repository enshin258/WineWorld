package com.wineworld.demo.dtos.order;

import com.wineworld.demo.dtos.orderposition.OrderPositionResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    private String status;

    private List<OrderPositionResponse> orderPositionResponses;
}
