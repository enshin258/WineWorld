package com.wineworld.demo.controllers;

import com.wineworld.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/user/add")
    public ResponseEntity<Void> addUser(){
        userService.addUser();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
