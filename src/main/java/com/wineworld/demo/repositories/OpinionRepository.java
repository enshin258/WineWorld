package com.wineworld.demo.repositories;

import com.wineworld.demo.entities.Opinion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpinionRepository extends JpaRepository<Opinion, Long> {
    Opinion findByUserUserIdAndProductProductId(Long userId, Long productId);
}
