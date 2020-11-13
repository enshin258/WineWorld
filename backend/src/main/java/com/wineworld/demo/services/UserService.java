package com.wineworld.demo.services;

import com.wineworld.demo.entities.User;
import com.wineworld.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public void addUser(){
        userRepository.save(new User(1,"Macius", "haselko", "macius@example.com"));
    }
}
