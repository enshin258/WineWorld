package com.wineworld.demo.dtos.genre;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreRequest {
    private String name;
}
