package com.wineworld.demo.services;

import com.wineworld.demo.config.ModelMapperConfig;
import com.wineworld.demo.dtos.order.OrderResponse;
import com.wineworld.demo.dtos.user.UserRequest;
import com.wineworld.demo.dtos.user.UserResponse;
import com.wineworld.demo.entities.User;
import com.wineworld.demo.repositories.RoleRepository;
import com.wineworld.demo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        modelMapper = ModelMapperConfig.getOpinionMapping();
        ModelMapperConfig.addUserMappings(modelMapper);
        ModelMapperConfig.getOrderPositionMapping(modelMapper);
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }

    public String addUser(UserRequest userRequest, boolean isAdmin){
        User user = modelMapper.map(userRequest, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(isAdmin) {
            //2L is for admin
            user.setRole(roleRepository.findById(2L).orElseThrow(EntityNotFoundException::new));
        }
        else {
            //1L is for normal user
            user.setRole(roleRepository.findById(1L).orElseThrow(EntityNotFoundException::new));
        }
        User createdUser = userRepository.save(user);
        return "Added " + createdUser.getLogin();
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




    public List<OrderResponse> getUserOrders(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(EntityNotFoundException::new);
        return user.getUserOrders().stream()
                .map(order -> modelMapper.map(order, OrderResponse.class))
                .collect(Collectors.toList());
    }

    public UserResponse findByLogin(String login){
        User user = userRepository.findByLogin(login).orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(user, UserResponse.class);
    }

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

    public UserResponse updateUser(Long userId, UserRequest userRequest){
        User userToUpdate = userRepository.getOne(userId);
        if(userRequest.getPassword() != null) {
            userToUpdate.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        }
        if(userRequest.getLogin() != null) {
            userToUpdate.setLogin(userRequest.getLogin());
        }
        if(userRequest.getEmail() != null) {
            userToUpdate.setEmail(userRequest.getEmail());
        }
        userRepository.save(userToUpdate);
        return modelMapper.map(userToUpdate, UserResponse.class);
    }

    public void deleteByLogin(String login){
        userRepository.deleteByLogin(login);
    }




}
