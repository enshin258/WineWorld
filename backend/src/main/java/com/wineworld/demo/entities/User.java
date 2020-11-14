package com.wineworld.demo.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @NonNull(message = "Login is required.")
    private String login;

    @NonNull(message = "Password is required.")
    private String password;

    @NonNull(message = "Email is required.")
    private String email;


}
