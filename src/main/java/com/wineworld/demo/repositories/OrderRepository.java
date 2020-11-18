package com.wineworld.demo.repositories;

import com.wineworld.demo.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findAllByUserUserId(Long userId);
    Order findByOrderId(Long orderId);
}
