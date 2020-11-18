package com.wineworld.demo.services;

import com.wineworld.demo.entities.Order;
import com.wineworld.demo.entities.OrderPosition;
import com.wineworld.demo.entities.Product;
import com.wineworld.demo.repositories.OrderPositionRepository;
import com.wineworld.demo.repositories.OrderRepository;
import com.wineworld.demo.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderPositionRepository orderPositionRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository, OrderPositionRepository orderPositionRepository) {
        this.orderRepository = orderRepository;
        this.orderPositionRepository = orderPositionRepository;
    }


    public Order getOrder(Long orderId){
        return orderRepository.findByOrderId(orderId);
    }

    public List<OrderPosition> getOrderPositions(Long orderId){
        Order order = orderRepository.findByOrderId(orderId);
        return order.getOrderPositions();
    }

    public OrderPosition addOrderPosition(OrderPosition orderPosition){
        orderPositionRepository.save(orderPosition);
        return orderPosition;
    }

    public Order addOrder(Order order){
        orderRepository.save(order);
        return order;
    }

    public List<Product> getAllProductsFromOrder(Long orderId){
        Order order = orderRepository.findByOrderId(orderId);
        List<OrderPosition> positions = order.getOrderPositions();
        List<Product> products = new ArrayList<>();
        for(OrderPosition position : positions){
            products.add(position.getProduct());
        }
        return products;
    }
}
