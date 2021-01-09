package com.wineworld.demo.dtos.opinion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OpinionResponse {
    private long opinionId;

    private String title;

    private String date;

    private int rating;

    private String comment;

    private long userId;

    private String login;

    private long productId;

    private String productName;

}
