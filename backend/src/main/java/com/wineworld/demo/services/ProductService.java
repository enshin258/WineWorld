package com.wineworld.demo.services;

import java.util.List;

import com.wineworld.demo.entities.Product;
import com.wineworld.demo.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }

    public void addProduct(){
        productRepository.save(new Product(1,
        "Amarena",
        "12.99",
        "https://placekitten.com/200/300",
        "Siarczan",
        new Location(12.9, 11.3, "Fabryka smaku", "Polska"),
        "Polskie browary S.A",
        12,
        2020,
        0.75));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}