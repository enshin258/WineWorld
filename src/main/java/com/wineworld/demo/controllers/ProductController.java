package com.wineworld.demo.controllers;

import com.wineworld.demo.entities.Location;
import com.wineworld.demo.entities.Product;
import com.wineworld.demo.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping("/products/add")
    public ResponseEntity<Product> addProduct(@RequestBody Product product){
        Location location = new Location(12.9f, 11.3f, "Fabryka smaku", "Polska");
        Product responeProduct = productService.addProduct(product, location);
        return new ResponseEntity<>(responeProduct, HttpStatus.OK);
    }

    @GetMapping("/products/get")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
