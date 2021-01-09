package com.wineworld.demo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Opinions")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Opinion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long opinionId;

    @NonNull
    private String title;

    @NonNull
    private String date;

    @NonNull
    private int rating;

    @NonNull
    private String comment;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;
}
