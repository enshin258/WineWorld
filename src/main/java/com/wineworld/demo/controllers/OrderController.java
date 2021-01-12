package com.wineworld.demo.controllers;

import com.wineworld.demo.dtos.order.OrderRequest;
import com.wineworld.demo.dtos.order.OrderResponse;
import com.wineworld.demo.dtos.orderposition.OrderPositionResponse;
import com.wineworld.demo.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService orderService;


    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/get/{orderId}")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long orderId){
        try {
            return new ResponseEntity<>(orderService.getOrder(orderId), HttpStatus.OK);
        }catch(EntityNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/position/get/all/{orderId}")
    public ResponseEntity<List<OrderPositionResponse>> getOrderPositions(@PathVariable Long orderId){
        return new ResponseEntity<>(orderService.getOrderPositions(orderId), HttpStatus.OK);
    }

//    @PostMapping("/positions/add")
//    public ResponseEntity<OrderPositionResponse> addOrderPosition(@RequestBody OrderPositionRequest positionRequest){
//        if(positionRequest != null){
//            return new ResponseEntity<>(orderService.addOrderPosition(positionRequest), HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

//    @PostMapping("/positions/add")
//    public ResponseEntity<List<OrderPositionResponse>> addOrderPosition(@RequestBody List<OrderPositionRequest> positionRequest){
//        if(positionRequest != null){
//            return new ResponseEntity<>(orderService.addOrderPositions(positionRequest), HttpStatus.OK);
//        }else{
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//        }
//    }

    @PostMapping("/make")
    public ResponseEntity<OrderResponse> addOrder(@RequestBody OrderRequest orderRequest){
        if(orderRequest != null){
            return new ResponseEntity<>(orderService.addOrder(orderRequest), HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


}
