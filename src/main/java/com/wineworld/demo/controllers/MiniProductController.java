package com.wineworld.demo.controllers;

import com.wineworld.demo.dtos.product.MiniProductResponse;
import com.wineworld.demo.services.MiniProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/mini")
public class MiniProductController {

    private final MiniProductService productService;

    public MiniProductController(MiniProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/get/{numberOfProducts}/{numberOfPage}")
    public ResponseEntity<List<MiniProductResponse>> getMiniProductsByPage(@PathVariable Integer numberOfProducts, @PathVariable Integer numberOfPage){
        return new ResponseEntity<>(productService.getMiniProductsByNumber(numberOfProducts, numberOfPage), HttpStatus.OK);
    }

    @GetMapping("/get/{numberOfProducts}/{numberOfPage}/{genreId}")
    public ResponseEntity<List<MiniProductResponse>> getMiniProductsByPageAndGenre(@PathVariable Integer numberOfProducts, @PathVariable Integer numberOfPage, @PathVariable Long genreId){
        return new ResponseEntity<>(productService.getMiniProductsByNumberAndByCategory(numberOfProducts, numberOfPage, genreId), HttpStatus.OK);
    }

    @GetMapping("get/by/name/{numberOfProducts}/{numberOfPage}/{name}")
    public ResponseEntity<List<MiniProductResponse>> getMiniProductsByName(@PathVariable Integer numberOfProducts,
                                                                           @PathVariable Integer numberOfPage,
                                                                           @PathVariable String name){
        return new ResponseEntity<>(productService.getMiniProductsByNumberAndBySearch(numberOfProducts, numberOfPage, name), HttpStatus.OK);
    }
}
