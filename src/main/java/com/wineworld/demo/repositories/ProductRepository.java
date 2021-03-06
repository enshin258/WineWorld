package com.wineworld.demo.repositories;

import com.wineworld.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByNameContaining(String name);
    Long countAllByGenreGenreId(Long genreId);
    Long countAllByNameContaining(String name);
}
