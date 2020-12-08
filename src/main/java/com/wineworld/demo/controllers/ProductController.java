package com.wineworld.demo.controllers;

import com.wineworld.demo.dtos.Opinion.OpinionResponse;
import com.wineworld.demo.dtos.location.LocationRequest;
import com.wineworld.demo.dtos.location.LocationResponse;
import com.wineworld.demo.dtos.product.ProductRequest;
import com.wineworld.demo.dtos.product.ProductResponse;
import com.wineworld.demo.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<ProductResponse> addProduct(@RequestBody ProductRequest productRequest){
        ProductResponse productResponse = productService.addProduct(productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("/location/add")
    public ResponseEntity<LocationResponse> addLocation(@RequestBody LocationRequest locationRequest){
        if(locationRequest != null){
           LocationResponse locationResponse = productService.addLocation(locationRequest);
           return new ResponseEntity<>(locationResponse, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/location/get/all")
    public ResponseEntity<List<LocationResponse>> getAllLocations(){
        return new ResponseEntity<>(productService.getAllLocations(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("products/get/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/get/opinions/{productId}")
    public ResponseEntity<List<OpinionResponse>> getOpinions(@PathVariable Long productId){
        return new ResponseEntity<>(productService.getAllOpinions(productId), HttpStatus.OK);
    }
}

