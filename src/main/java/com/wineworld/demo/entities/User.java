package com.wineworld.demo.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Users")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @NonNull
    @Column(unique = true)
    private String login;

    @NonNull
    private String password;

    @NonNull
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "user")
    private List<Order> userOrders;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Opinion> opinions;

    @ManyToOne
    @NonNull
    private Role role;


}
