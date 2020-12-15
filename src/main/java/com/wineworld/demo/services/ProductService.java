package com.wineworld.demo.services;

import com.wineworld.demo.config.ModelMapperConfig;
import com.wineworld.demo.dtos.count.CountResponse;
import com.wineworld.demo.dtos.genre.GenreRequest;
import com.wineworld.demo.dtos.genre.GenreResponse;
import com.wineworld.demo.dtos.opinion.OpinionResponse;
import com.wineworld.demo.dtos.location.LocationRequest;
import com.wineworld.demo.dtos.location.LocationResponse;
import com.wineworld.demo.dtos.product.MiniProductResponse;
import com.wineworld.demo.dtos.product.ProductRequest;
import com.wineworld.demo.dtos.product.ProductResponse;
import com.wineworld.demo.entities.Genre;
import com.wineworld.demo.entities.Location;
import com.wineworld.demo.entities.Product;
import com.wineworld.demo.repositories.GenreRepository;
import com.wineworld.demo.repositories.LocationRepository;
import com.wineworld.demo.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional

public class ProductService {
    private final ProductRepository productRepository;
    private final LocationRepository locationRepository;
    private final GenreRepository genreRepository;
    private ModelMapper modelMapper;

    public ProductService(ProductRepository productRepository, LocationRepository locationRepository, GenreRepository genreRepository){
        this.productRepository = productRepository;
        this.locationRepository = locationRepository;
        this.genreRepository = genreRepository;
        modelMapper = ModelMapperConfig.getOpinionMapping();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        modelMapper = ModelMapperConfig.addProductMappings(modelMapper);
    }

    public ProductResponse addProduct(ProductRequest productRequest){
        Location location = locationRepository.findById(productRequest.getLocationId())
                .orElseThrow(EntityNotFoundException::new);
        Genre genre = genreRepository.findById(productRequest.getGenreId())
                .orElseThrow(EntityNotFoundException::new);
        Product product = modelMapper.map(productRequest, Product.class);
        product.setLocation(location);
        product.setGenre(genre);
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
        Product product = productRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(product, ProductResponse.class);
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

    public CountResponse getProductCount(){
        CountResponse count = new CountResponse();
        count.setCount(productRepository.count());
        return count;
    }

    public List<MiniProductResponse> getAllProductsMini(){
        return productRepository.findAll().stream()
                .map(product -> modelMapper.map(product, MiniProductResponse.class))
                .collect(Collectors.toList());
    }

    public List<MiniProductResponse> getProductByName(String name){
        return productRepository.findAllByNameContaining(name).stream()
                .map(product -> modelMapper.map(product, MiniProductResponse.class))
                .collect(Collectors.toList());
    }

   public GenreResponse addGenre(GenreRequest genreRequest){
       Genre genre = modelMapper.map(genreRequest, Genre.class);
       Genre createdGenre = genreRepository.save(genre);
       return modelMapper.map(createdGenre, GenreResponse.class);
   }

   public void deleteGenre(Long genreId){
        genreRepository.deleteById(genreId);
   }

   public List<GenreResponse> getAllGenres(){
        return genreRepository.findAll().stream()
                .map(genre -> modelMapper.map(genre, GenreResponse.class))
                .collect(Collectors.toList());
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

    public CountResponse getProductCountByGenre(Long genreId){
        CountResponse count = new CountResponse();
        count.setCount(productRepository.countAllByGenreGenreId(genreId));
        return count;
    }

    public CountResponse getProductCountByName(String name){
        CountResponse count = new CountResponse();
        count.setCount(productRepository.countAllByNameContaining(name));
        return count;
    }






}
