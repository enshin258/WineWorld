package com.wineworld.demo.dtos.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private long userId;

    private String login;

    private String password;

    private String email;

    private String roleName;

}
