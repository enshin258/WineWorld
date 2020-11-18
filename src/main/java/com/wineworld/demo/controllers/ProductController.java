package com.wineworld.demo.controllers;

import com.wineworld.demo.entities.Location;
import com.wineworld.demo.entities.Product;
import com.wineworld.demo.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        Product responseProduct = productService.addProduct(product, location);
        return new ResponseEntity<>(responseProduct, HttpStatus.OK);
    }

    @GetMapping("/products/get/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping("/products/location/add")
    public ResponseEntity<Location> addLocation(@RequestBody Location location){
        if(location != null){
            Location responseLocation = productService.addLocation(location);
            return new ResponseEntity<>(responseLocation, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("products/location/get/all")
    public ResponseEntity<List<Location>> getAllLocations(){
        List<Location> locations = productService.getAllLocations();
        return new ResponseEntity<>(locations, HttpStatus.OK);
    }

    @DeleteMapping("products/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("products/get/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id){
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }
}

