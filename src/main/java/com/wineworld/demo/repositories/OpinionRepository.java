package com.wineworld.demo.repositories;

import com.wineworld.demo.entities.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    Opinion findByUserUserIdAndProductProductId(Long userId, Long productId);
    List<Opinion> findAllByProduct(Long productId);
}
