package com.wineworld.demo.dtos.user;

import java.util.Optional;

import com.wineworld.demo.entities.User;
import com.wineworld.demo.repositories.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService {


    private final UserRepository userRepository;

    public UserDetailsServiceImplementation(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByLogin(username);

        UserPrincipal userPrincipal = user.map(UserPrincipal::new).orElseThrow(()-> new UsernameNotFoundException("NIE MA TAKIEGO UZYTKOWNIKA"));

        return userPrincipal;
    }
}
