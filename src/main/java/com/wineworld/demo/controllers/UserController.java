package com.wineworld.demo.controllers;

import com.wineworld.demo.dtos.order.OrderResponse;
import com.wineworld.demo.dtos.user.UserRequest;
import com.wineworld.demo.dtos.user.UserResponse;
import com.wineworld.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PostMapping("/register/{isAdmin}")
    public ResponseEntity<String> addUser(@RequestBody UserRequest userRequest, @PathVariable Boolean isAdmin){
        if(userRequest != null) {
            String userResponse = userService.addUser(userRequest, isAdmin);
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<UserResponse>>getAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserResponse> getUserByID(@PathVariable Long id){
        UserResponse userResponse = userService.getUserByID(id);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping("/get/orders/{userId}")
    public ResponseEntity<List<OrderResponse>> getUserOrders(@PathVariable Long userId){
        return new ResponseEntity<>(userService.getUserOrders(userId), HttpStatus.OK);
    }

    @GetMapping("/get/by/login/{login}")
    public ResponseEntity<UserResponse> getUserByLogin(@PathVariable String login) {
        return new ResponseEntity<>(userService.findByLogin(login), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/renew/{userId}")
    public ResponseEntity<UserResponse> updateUser(@PathVariable Long userId, @RequestBody UserRequest userRequest){
        return new ResponseEntity<>(userService.updateUser(userId, userRequest), HttpStatus.OK);
    }

    @DeleteMapping("/delete/by/name/{login}")
    public ResponseEntity<Void> deleteUserByName(@PathVariable String login){
        userService.deleteByLogin(login);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
