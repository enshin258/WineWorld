package com.wineworld.demo.dtos.Opinion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpinionRequest {
    private int rating;

    private String comment;

    private long userId;

    private long productId;
}
