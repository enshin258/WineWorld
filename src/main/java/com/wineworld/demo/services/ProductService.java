package com.wineworld.demo.services;

import java.util.List;

import com.wineworld.demo.entities.Location;
import com.wineworld.demo.entities.Product;
import com.wineworld.demo.repositories.LocationRepository;
import com.wineworld.demo.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional

public class ProductService {
    private final ProductRepository productRepository;
    private final LocationRepository locationRepository;

    public ProductService(ProductRepository productRepository, LocationRepository locationRepository){
        this.productRepository = productRepository;
        this.locationRepository = locationRepository;
    }

    public Product addProduct(Product product, Location location){
        locationRepository.save(location);
        product.setLocation(location);
        productRepository.save(product);

        return product;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}