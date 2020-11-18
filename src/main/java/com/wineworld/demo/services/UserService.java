package com.wineworld.demo.services;

import com.wineworld.demo.entities.Opinion;
import com.wineworld.demo.entities.User;
import com.wineworld.demo.repositories.OpinionRepository;
import com.wineworld.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final OpinionRepository opinionRepository;

    public UserService(UserRepository userRepository, OpinionRepository opinionRepository){
        this.userRepository = userRepository;
        this.opinionRepository = opinionRepository;
    }

    public User addUser(User user){
        userRepository.save(user);
        return user;
    }

    public User getUserByID(Long id){
        return userRepository.findUserByUserId(id);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Opinion addOpinion(Opinion opinion){
        opinionRepository.save(opinion);
        return opinion;
    }

    public void removeOpinion(Long userId, Long productId){
        Opinion opinion = opinionRepository.findByUserUserIdAndProductProductId(userId, productId);
        opinionRepository.delete(opinion);
    }
}
