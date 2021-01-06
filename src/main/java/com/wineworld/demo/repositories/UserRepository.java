package com.wineworld.demo.repositories;

import java.util.Optional;

import com.wineworld.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByLogin(String login);
    void deleteByLogin(String login);
}
