package com.wineworld.demo.controllers;

import com.wineworld.demo.entities.Order;
import com.wineworld.demo.entities.OrderPosition;
import com.wineworld.demo.entities.Product;
import com.wineworld.demo.entities.User;
import com.wineworld.demo.services.OrderService;
import com.wineworld.demo.services.ProductService;
import com.wineworld.demo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;
    private final ProductService productService;

    public OrderController(OrderService orderService, UserService userService, ProductService productService) {
        this.orderService = orderService;
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/order/get/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable Long orderId){
        Order order = orderService.getOrder(orderId);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @GetMapping("/order/position/get/all/{orderId}")
    public ResponseEntity<List<OrderPosition>> getOrderPositions(@PathVariable Long orderId){
        List<OrderPosition> orderPositions = orderService.getOrderPositions(orderId);
        return new ResponseEntity<>(orderPositions, HttpStatus.OK);
    }

    @PostMapping("/order/position/add/{orderId}/{productId}")
    public ResponseEntity<OrderPosition> addOrderPositions(@RequestBody OrderPosition orderPosition, @PathVariable Long orderId, @PathVariable Long productId){
        if(orderPosition != null){
            Product product = productService.getProductById(productId);
            Order order = orderService.getOrder(orderId);
            orderPosition.setProduct(product);
            orderPosition.setOrder(order);
            OrderPosition responseOrderPosition = orderService.addOrderPosition(orderPosition);
            return new ResponseEntity<>(responseOrderPosition, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/order/add/{userId}")
    public ResponseEntity<Order> addOrder(@RequestBody Order order, @PathVariable Long userId){
        if(order != null){
            User user = userService.getUserByID(userId);
            order.setUser(user);
            Order responseOrder = orderService.addOrder(order);
            return new ResponseEntity<>(responseOrder, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/order/get/all/products/{orderId}")
    public ResponseEntity<List<Product>> getAllProductsFromOrder(@PathVariable Long orderId){
        List<Product> products = orderService.getAllProductsFromOrder(orderId);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
