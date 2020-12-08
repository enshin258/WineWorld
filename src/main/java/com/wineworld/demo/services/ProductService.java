package com.wineworld.demo.services;

import com.wineworld.demo.config.ModelMapperConfig;
import com.wineworld.demo.dtos.Opinion.OpinionResponse;
import com.wineworld.demo.dtos.location.LocationRequest;
import com.wineworld.demo.dtos.location.LocationResponse;
import com.wineworld.demo.dtos.product.ProductRequest;
import com.wineworld.demo.dtos.product.ProductResponse;
import com.wineworld.demo.entities.Location;
import com.wineworld.demo.entities.Product;
import com.wineworld.demo.repositories.LocationRepository;
import com.wineworld.demo.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional

public class ProductService {
    private final ProductRepository productRepository;
    private final LocationRepository locationRepository;
    private ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, LocationRepository locationRepository){
        this.productRepository = productRepository;
        this.locationRepository = locationRepository;
        modelMapper = ModelMapperConfig.getOpinionMapping();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public ProductResponse addProduct(ProductRequest productRequest){
        Location location = locationRepository.findById(productRequest.getLocationId())
                .orElseThrow(EntityNotFoundException::new);
        Product product = modelMapper.map(productRequest, Product.class);
        product.setLocation(location);
        Product createdProduct = productRepository.save(product);
        return modelMapper.map(createdProduct, ProductResponse.class);
    }

    public LocationResponse addLocation(LocationRequest locationRequest){
        Location location = modelMapper.map(locationRequest, Location.class);
        Location createdLocation = locationRepository.save(location);
        return modelMapper.map(createdLocation, LocationResponse.class);
    }

    public List<LocationResponse> getAllLocations(){
        return locationRepository.findAll().stream()
                .map(location -> modelMapper.map(location, LocationResponse.class))
                .collect(Collectors.toList());
    }

    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }

    public ProductResponse getProductById(Long id){
        return modelMapper.map(productRepository.findById(id), ProductResponse.class);
    }

    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, ProductResponse.class))
                .collect(Collectors.toList());
    }

    public List<OpinionResponse> getAllOpinions(Long productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(EntityNotFoundException::new);
        return product.getOpinions().stream()
                .map(opinion -> modelMapper.map(opinion, OpinionResponse.class))
                .collect(Collectors.toList());
    }
}
