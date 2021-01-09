package com.wineworld.demo.services;

import com.wineworld.demo.config.ModelMapperConfig;
import com.wineworld.demo.dtos.opinion.OpinionRequest;
import com.wineworld.demo.dtos.opinion.OpinionResponse;
import com.wineworld.demo.entities.Opinion;
import com.wineworld.demo.entities.Product;
import com.wineworld.demo.entities.User;
import com.wineworld.demo.repositories.OpinionRepository;
import com.wineworld.demo.repositories.ProductRepository;
import com.wineworld.demo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OpinionService {
    private ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OpinionRepository opinionRepository;

    public OpinionService(UserRepository userRepository, ProductRepository productRepository, OpinionRepository opinionRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.opinionRepository = opinionRepository;
        modelMapper = ModelMapperConfig.getOpinionMapping();
        ModelMapperConfig.addProductMappings(modelMapper);
    }

    public OpinionResponse addOpinion(OpinionRequest opinionRequest){
        Opinion opinion = modelMapper.map(opinionRequest, Opinion.class);
        User user = userRepository.findById(opinionRequest.getUserId())
                .orElseThrow(EntityNotFoundException::new);
        Product product = productRepository.findById(opinionRequest.getProductId())
                .orElseThrow(EntityNotFoundException::new);
        opinion.setProduct(product);
        opinion.setUser(user);
        Opinion createdOpinion = opinionRepository.save(opinion);
        return modelMapper.map(createdOpinion, OpinionResponse.class);
    }


    public void removeOpinion(Long userId, Long productId){
        Opinion opinion = opinionRepository.findByUserUserIdAndProductProductId(userId, productId);
        opinionRepository.delete(opinion);
    }


    public List<OpinionResponse> getAllOpinions(Long productId){
        Product product = productRepository.findById(productId)
                .orElseThrow(EntityNotFoundException::new);
        return product.getOpinions().stream()
                .map(opinion -> modelMapper.map(opinion, OpinionResponse.class))
                .collect(Collectors.toList());
    }


    public List<OpinionResponse> getUserOpinions(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
        return user.getOpinions().stream()
                .map(opinion -> modelMapper.map(opinion, OpinionResponse.class))
                .collect(Collectors.toList());
    }


    public OpinionResponse updateOpinion(Long opinionId, OpinionRequest opinionRequest){
        Opinion opinionToUpdate = opinionRepository.getOne(opinionId);
        opinionToUpdate.setComment(opinionRequest.getComment());
        opinionToUpdate.setRating(opinionRequest.getRating());
        opinionRepository.save(opinionToUpdate);
        return modelMapper.map(opinionToUpdate, OpinionResponse.class);
    }


}
