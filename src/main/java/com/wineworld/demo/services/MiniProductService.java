package com.wineworld.demo.services;

import com.wineworld.demo.config.ModelMapperConfig;
import com.wineworld.demo.dtos.product.MiniProductResponse;
import com.wineworld.demo.entities.Genre;
import com.wineworld.demo.entities.Product;
import com.wineworld.demo.repositories.GenreRepository;
import com.wineworld.demo.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MiniProductService {
    private final ProductRepository productRepository;
    private final GenreRepository genreRepository;
    private ModelMapper modelMapper;

    public MiniProductService(ProductRepository productRepository, GenreRepository genreRepository) {
        this.productRepository = productRepository;
        this.genreRepository = genreRepository;
        modelMapper = new ModelMapper();
        ModelMapperConfig.addProductMappings(modelMapper);
    }

    public List<MiniProductResponse> getMiniProductsByNumber(int numberOfProducts, int numberOfPage){
        List<Product> products = productRepository.findAll();
        long productCount = productRepository.count();
        return getMiniProductResponses(numberOfProducts, numberOfPage, products, productCount);
    }

    public List<MiniProductResponse> getMiniProductsByNumberAndByCategory(int numberOfProducts, int numberOfPage, Long genreId){
        Genre genre = genreRepository.findById(genreId)
                .orElseThrow(EntityNotFoundException::new);
        List<Product> products = genre.getProducts();
        long productCount = productRepository.countAllByGenreGenreId(genreId);
        return getMiniProductResponses(numberOfProducts, numberOfPage, products, productCount);
    }


    public List<MiniProductResponse> getMiniProductsByNumberAndBySearch(int numberOfProducts, int numberOfPage, String name){
        List<Product> products = productRepository.findAllByNameContaining(name);
        long productCount = productRepository.countAllByNameContaining(name);
        return getMiniProductResponses(numberOfProducts, numberOfPage, products, productCount);
    }

    private List<MiniProductResponse> getMiniProductResponses(int numberOfProducts, int numberOfPage, List<Product> products, long productCount) {
        int totalPages = (int) Math.ceil((float) productCount/numberOfProducts);
        if(numberOfPage > totalPages){
            return new ArrayList<>();
        }
        return getDividedProducts(numberOfProducts, productCount , products).get(numberOfPage-1).stream()
                .map(product -> modelMapper.map(product, MiniProductResponse.class))
                .collect(Collectors.toList());
    }

    private List<List<Product>> getDividedProducts(int numberOfProducts, long totalProductsCount, List<Product> products){
        List<List<Product>> productsDivided = new ArrayList<>();
        for(int i = 0; i<totalProductsCount; i+=numberOfProducts){
            productsDivided.add(new ArrayList<>(
                    products.subList(i, (int) Math.min(totalProductsCount, i + numberOfProducts))
            ));
        }
        return productsDivided;
    }
}
