package com.wineworld.demo.services;

import com.wineworld.demo.config.ModelMapperConfig;
import com.wineworld.demo.dtos.count.CountResponse;
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
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final LocationRepository locationRepository;
    private final GenreRepository genreRepository;
    private ModelMapper modelMapper;
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");


    public ProductService(ProductRepository productRepository, LocationRepository locationRepository, GenreRepository genreRepository){
        this.productRepository = productRepository;
        this.locationRepository = locationRepository;
        this.genreRepository = genreRepository;
        modelMapper = ModelMapperConfig.getOpinionMapping();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
        ModelMapperConfig.addProductMappings(modelMapper);
    }

    public ProductResponse addProduct(ProductRequest productRequest){
        Location location = locationRepository.findById(productRequest.getLocationId())
                .orElseThrow(EntityNotFoundException::new);
        Genre genre = genreRepository.findById(productRequest.getGenreId())
                .orElseThrow(EntityNotFoundException::new);
        Product product = modelMapper.map(productRequest, Product.class);
        product.setLocation(location);
        product.setGenre(genre);

        //save image
        String fileName = saveImage(productRequest.getPicture());

        product.setPictureUrl("http://localhost:8080/images/" + fileName);

        Product createdProduct = productRepository.save(product);

        return modelMapper.map(createdProduct, ProductResponse.class);
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

    public ProductResponse updateProduct(Long productId, ProductRequest productRequest){
        Product productToUpdate = productRepository.getOne(productId);
        productToUpdate.setGenre(genreRepository.findById(productRequest.getGenreId()).orElseThrow(EntityNotFoundException::new));
        productToUpdate.setLocation(locationRepository.findById(productRequest.getLocationId()).orElseThrow(EntityNotFoundException::new));
        productToUpdate.setAlcoholLevel(productRequest.getAlcoholLevel());
        productToUpdate.setName(productRequest.getName());
        String fileName = saveImage(productRequest.getPicture());
        productToUpdate.setPictureUrl("http://localhost:8080/images/" + fileName);
        deleteImage(fileName);
        productToUpdate.setPrice(productRequest.getPrice());
        productToUpdate.setProducer(productRequest.getProducer());
        productToUpdate.setVolume(productRequest.getVolume());
        productToUpdate.setYear(productRequest.getYear());
        productRepository.save(productToUpdate);
        return modelMapper.map(productToUpdate, ProductResponse.class);
    }

    private String saveImage(MultipartFile picture){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String fileName = timestamp.getTime() + ".jpg";

        InputStream inputStream;
        OutputStream outputStream;

        File newFile = new File("src\\main\\resources\\static\\images\\" + fileName);

        try {
            inputStream = picture.getInputStream();

            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            outputStream = new FileOutputStream(newFile);
            int read;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }

    private void deleteImage(String fileName){
        File newFile = new File("src\\main\\resources\\static\\images\\" + fileName);
        newFile.delete();
    }
}
