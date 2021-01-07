package com.wineworld.demo.dtos.order;

import com.wineworld.demo.dtos.orderposition.OrderPositionRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {
    private String date;

    private String addressLineOne;

    private String addressLineTwo;

    private String city;

    private String postalCode;

    private String countryCode;

    private float totalCost;

    private String phoneNumber;

    private long userId;

    private String status;

    private List<OrderPositionRequest> orderPositionRequests;

}
