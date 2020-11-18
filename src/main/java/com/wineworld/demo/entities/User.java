package com.wineworld.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

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
    private String login;

    @NonNull
    private String password;

    @NonNull
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Order> userOrders;

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Opinion> opinions;


}
