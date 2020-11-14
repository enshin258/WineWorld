package com.wineworld.demo.controllers;

import com.wineworld.demo.entities.User;
import com.wineworld.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping("/user/add")
    public ResponseEntity<Void> addUser(){
        userService.addUser();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/user/get")
    public ResponseEntity<List<User>>getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
