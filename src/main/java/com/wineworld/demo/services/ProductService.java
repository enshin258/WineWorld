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

    public void addProduct(){
        Location location =  new Location(12.9f, 11.3f, "Fabryka smaku", "Polska");
        locationRepository.save(location);
        productRepository.save(new Product("Amarena",
        12.99f,
        "https://placekitten.com/200/300",
        "Siarczan",
        location,
        "Polskie browary S.A",
        12,
        2020,
        0.75f));
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}