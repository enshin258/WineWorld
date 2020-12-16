package com.wineworld.demo.services;

import com.wineworld.demo.config.ModelMapperConfig;
import com.wineworld.demo.dtos.opinion.OpinionRequest;
import com.wineworld.demo.dtos.opinion.OpinionResponse;
import com.wineworld.demo.dtos.order.OrderResponse;
import com.wineworld.demo.dtos.user.UserRequest;
import com.wineworld.demo.dtos.user.UserResponse;
import com.wineworld.demo.entities.Opinion;
import com.wineworld.demo.entities.Product;
import com.wineworld.demo.entities.User;
import com.wineworld.demo.repositories.OpinionRepository;
import com.wineworld.demo.repositories.ProductRepository;
import com.wineworld.demo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    private ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final OpinionRepository opinionRepository;
    private final ProductRepository productRepository;

    public UserService(UserRepository userRepository, OpinionRepository opinionRepository, ProductRepository productRepository){
        this.userRepository = userRepository;
        this.opinionRepository = opinionRepository;
        this.productRepository = productRepository;
        modelMapper = ModelMapperConfig.getOpinionMapping();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public UserResponse addUser(UserRequest userRequest){
        User user = modelMapper.map(userRequest, User.class);
        User createdUser = userRepository.save(user);
        return modelMapper.map(createdUser, UserResponse.class);
    }

    public UserResponse getUserByID(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(user, UserResponse.class);
    }

    public List<UserResponse> getAllUsers(){
        return userRepository.findAll().stream()
                .map(user -> modelMapper.map(user, UserResponse.class))
                .collect(Collectors.toList());
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

    public List<OrderResponse> getUserOrders(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
        return user.getUserOrders().stream()
                .map(order -> modelMapper.map(order, OrderResponse.class))
                .collect(Collectors.toList());
    }

    public List<OpinionResponse> getUserOpinions(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
        return user.getOpinions().stream()
                .map(opinion -> modelMapper.map(opinion, OpinionResponse.class))
                .collect(Collectors.toList());
    }

    public UserResponse findByLogin(String login){
        User user = userRepository.findByLogin(login).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(user, UserResponse.class);
    }
}
