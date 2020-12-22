package com.wineworld.demo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Genres")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long genreId;

    @NonNull
    private String name;

    @OneToMany(mappedBy = "genre", cascade = CascadeType.REMOVE)
    List<Product> products;
}
