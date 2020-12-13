package com.wineworld.demo.dtos.genre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GenreResponse {
    private long genreId;

    private String name;
}
