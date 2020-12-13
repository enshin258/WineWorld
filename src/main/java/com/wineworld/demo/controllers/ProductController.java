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

    @GetMapping("/get/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id){
        return new ResponseEntity<>(productService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping("/get/opinions/{productId}")
    public ResponseEntity<List<OpinionResponse>> getOpinions(@PathVariable Long productId){
        return new ResponseEntity<>(productService.getAllOpinions(productId), HttpStatus.OK);
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

    @PostMapping("/add/genre")
    public ResponseEntity<GenreResponse> addGenre(@RequestBody GenreRequest genreRequest){
        if(genreRequest != null){
            GenreResponse genreResponse = productService.addGenre(genreRequest);
            return new ResponseEntity<>(genreResponse, HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/genres")
    public ResponseEntity<List<GenreResponse>> getGenres(){
        return new ResponseEntity<>(productService.getAllGenres(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/genre/{genreId}")
    public ResponseEntity<Void> deleteGenre(@PathVariable Long genreId){
        productService.deleteGenre(genreId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/get/mini/{numberOfProducts}/{numberOfPage}")
    public ResponseEntity<List<MiniProductResponse>> getMiniProductsByPage(@PathVariable Integer numberOfProducts, @PathVariable Integer numberOfPage){
        return new ResponseEntity<>(productService.getMiniProductsByNumber(numberOfProducts, numberOfPage), HttpStatus.OK);
    }

    @GetMapping("/get/mini/{numberOfProducts}/{numberOfPage}/{genreId}")
    public ResponseEntity<List<MiniProductResponse>> getMiniProductsByPageAndGenre(@PathVariable Integer numberOfProducts, @PathVariable Integer numberOfPage, @PathVariable Long genreId){
        return new ResponseEntity<>(productService.getMiniProductsByNumberAndByCategory(numberOfProducts, numberOfPage, genreId), HttpStatus.OK);
    }

    @GetMapping("/get/count/{genreId}")
    public ResponseEntity<CountResponse> getProductCountByGenre(@PathVariable Long genreId){
        return new ResponseEntity<>(productService.getProductCountByGenre(genreId), HttpStatus.OK);
    }

    @GetMapping("/get/count/by/name/{name}")
    public ResponseEntity<CountResponse> getProductCountByName(@PathVariable String name){
        return new ResponseEntity<>(productService.getProductCountByName(name), HttpStatus.OK);
    }
}

