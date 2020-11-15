package com.wineworld.demo.services;

import com.wineworld.demo.entities.User;
import com.wineworld.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void addUser(){
        userRepository.save(new User("Macius", "haselko", "macius@example.com"));
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
