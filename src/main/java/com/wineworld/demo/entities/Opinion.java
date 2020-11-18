package com.wineworld.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private int rating;

    @NonNull
    private String comment;

    @JsonIgnore
    @ManyToOne
    private User user;

    @JsonIgnore
    @ManyToOne
    private Product product;
}
