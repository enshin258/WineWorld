package com.wineworld.demo.dtos.OrderPosition;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderPositionRequest {
    private long orderId;

    private long productId;
}
