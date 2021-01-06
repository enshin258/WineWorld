package com.wineworld.demo.dtos.role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleLoginResponse {
    private long roleId;
    private String name;
    private long userId;
}
