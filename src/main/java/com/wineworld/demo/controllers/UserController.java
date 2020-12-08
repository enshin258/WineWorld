package com.wineworld.demo.controllers;

import com.wineworld.demo.dtos.Opinion.OpinionRequest;
import com.wineworld.demo.dtos.Opinion.OpinionResponse;
import com.wineworld.demo.dtos.Order.OrderResponse;
import com.wineworld.demo.dtos.user.UserRequest;
import com.wineworld.demo.dtos.user.UserResponse;
import com.wineworld.demo.services.ProductService;
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
    private final ProductService productService;

    public UserController(UserService userService, ProductService productService){
        this.userService = userService;
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest){
        if(userRequest != null) {
            UserResponse userResponse = userService.addUser(userRequest);
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




    @PostMapping("add/opinion")
    public ResponseEntity<OpinionResponse> addOpinion(@RequestBody OpinionRequest opinionRequest){
        if(opinionRequest != null){
            return new ResponseEntity<>(userService.addOpinion(opinionRequest), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/opinion/{userId}/{productId}")
    public ResponseEntity<Void> removeOpinion(@PathVariable Long userId, @PathVariable Long productId){
        userService.removeOpinion(userId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("get/orders/{userId}")
    public ResponseEntity<List<OrderResponse>> getUserOrders(@PathVariable Long userId){
        return new ResponseEntity<>(userService.getUserOrders(userId), HttpStatus.OK);
    }

    @GetMapping("get/opinions/{userId}")
    public ResponseEntity<List<OpinionResponse>> getUserOpinions(@PathVariable Long userId){
        return new ResponseEntity<>(userService.getUserOpinions(userId), HttpStatus.OK);
    }

}
