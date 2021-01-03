package com.wineworld.demo.controllers;

import com.wineworld.demo.dtos.count.CountResponse;
import com.wineworld.demo.dtos.genre.GenreRequest;
import com.wineworld.demo.dtos.genre.GenreResponse;
import com.wineworld.demo.dtos.opinion.OpinionResponse;
import com.wineworld.demo.dtos.location.LocationRequest;
import com.wineworld.demo.dtos.location.LocationResponse;
import com.wineworld.demo.dtos.product.MiniProductResponse;
import com.wineworld.demo.dtos.product.ProductRequest;
import com.wineworld.demo.dtos.product.ProductResponse;
import com.wineworld.demo.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }

    @PostMapping(value = "/add", consumes = "multipart/form-data")
    public ResponseEntity<ProductResponse> addProduct(@RequestParam("name") String name,
    @RequestParam("price") Float price,
    @RequestParam("picture") MultipartFile picture,
    @RequestParam("productDescription") String productDescription,
    @RequestParam("genreId") Long genreId,
    @RequestParam("locationId") Long locationId,
    @RequestParam("producer") String producer,
    @RequestParam("alcoholLevel") Float alcoholLevel,
    @RequestParam("year") Integer year,
    @RequestParam("volume") Float volume
    ){

        ProductRequest productRequest = new ProductRequest(name,
        price, picture, productDescription, genreId, locationId, producer, alcoholLevel, year, volume);

        ProductResponse productResponse = productService.addProduct(productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    @GetMapping("/get/all")
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/get/count")
    public ResponseEntity<CountResponse> getProductCount(){
        return new ResponseEntity<>(productService.getProductCount(), HttpStatus.OK);
    }

    @GetMapping("/get/all/mini")
    public ResponseEntity<List<MiniProductResponse>> getAllProductsMini(){
        return new ResponseEntity<>(productService.getAllProductsMini(), HttpStatus.OK);
    }

    @GetMapping("/get/by/name/{name}")
    public ResponseEntity<List<MiniProductResponse>> getProductByName(@PathVariable String name){
        return new ResponseEntity<>(productService.getProductByName(name), HttpStatus.OK);
    }


    @GetMapping("/get/count/{genreId}")
    public ResponseEntity<CountResponse> getProductCountByGenre(@PathVariable Long genreId){
        return new ResponseEntity<>(productService.getProductCountByGenre(genreId), HttpStatus.OK);
    }

    @GetMapping("/get/count/by/name/{name}")
    public ResponseEntity<CountResponse> getProductCountByName(@PathVariable String name){
        return new ResponseEntity<>(productService.getProductCountByName(name), HttpStatus.OK);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long productId, @RequestBody ProductRequest productRequest){
        return new ResponseEntity<>(productService.updateProduct(productId, productRequest), HttpStatus.OK);
    }


}

