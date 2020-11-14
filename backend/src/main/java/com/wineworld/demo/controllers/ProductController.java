package com.wineworld.demo.controllers;

import com.wineworld.demo.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @RequestMapping("/products/add")
    public ResponseEntity<Void> addProduct(){
        productService.addProduct();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/products/get")
    public ResponseEntity<Void> getAllProducts() {
        return new ResponseEntity<>(HttpStatus.OK).body(productService.getAllProducts());
    }
}
