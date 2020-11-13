package com.wineworld.demo.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "Users")
@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
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

}
