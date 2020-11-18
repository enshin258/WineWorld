package com.wineworld.demo.controllers;

import com.wineworld.demo.entities.Opinion;
import com.wineworld.demo.entities.Order;
import com.wineworld.demo.entities.Product;
import com.wineworld.demo.entities.User;
import com.wineworld.demo.services.ProductService;
import com.wineworld.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {
    private final UserService userService;
    private final ProductService productService;

    public UserController(UserService userService, ProductService productService){
        this.userService = userService;
        this.productService = productService;
    }

    @PostMapping("/user/add")
    public ResponseEntity<User> addUser(@RequestBody User user){
        if(user != null) {
            User responseUser = userService.addUser(user);
            return new ResponseEntity<>(responseUser, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/user/get/all")
    public ResponseEntity<List<User>>getAllUsers() {
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("user/get/{id}")
    public ResponseEntity<User> getUserByID(@PathVariable Long id){
        User user = userService.getUserByID(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/user/get/all/orders/{userId}")
    public ResponseEntity<List<Order>> getUserOrders(@PathVariable Long userId){
        User user = userService.getUserByID(userId);
        List<Order> orders = user.getUserOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @PostMapping("/add/opinion/{userId}/{productId}")
    public ResponseEntity<Opinion> addOpinion(@RequestBody Opinion opinion, @PathVariable Long userId, @PathVariable Long productId){
        if(opinion != null){
            User user = userService.getUserByID(userId);
            opinion.setUser(user);
            Product product = productService.getProductById(productId);
            opinion.setProduct(product);
            Opinion responseOpinion = userService.addOpinion(opinion);
            return new ResponseEntity<>(responseOpinion, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/opinion/{userId}/{productId}")
    public ResponseEntity<Void> removeOpinion(@PathVariable Long userId, @PathVariable Long productId){
        userService.removeOpinion(userId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
