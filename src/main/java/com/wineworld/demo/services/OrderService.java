package com.wineworld.demo.services;

import com.wineworld.demo.config.ModelMapperConfig;
import com.wineworld.demo.dtos.order.OrderRequest;
import com.wineworld.demo.dtos.order.OrderResponse;
import com.wineworld.demo.dtos.orderposition.OrderPositionRequest;
import com.wineworld.demo.dtos.orderposition.OrderPositionResponse;
import com.wineworld.demo.entities.Order;
import com.wineworld.demo.entities.OrderPosition;
import com.wineworld.demo.entities.Product;
import com.wineworld.demo.entities.User;
import com.wineworld.demo.repositories.OrderPositionRepository;
import com.wineworld.demo.repositories.OrderRepository;
import com.wineworld.demo.repositories.ProductRepository;
import com.wineworld.demo.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    private final OrderPositionRepository orderPositionRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private ModelMapper modelMapper;

    public OrderService(OrderRepository orderRepository, OrderPositionRepository orderPositionRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderPositionRepository = orderPositionRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        modelMapper = ModelMapperConfig.getOrderPositionMapping();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    }


    public OrderResponse getOrder(Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        return modelMapper.map(order, OrderResponse.class);
    }

    public List<OrderPositionResponse> getOrderPositions(Long orderId){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(EntityNotFoundException::new);
        return order.getOrderPositions().stream()
                .map(orderPosition -> modelMapper.map(orderPosition, OrderPositionResponse.class))
                .collect(Collectors.toList());
    }

//    public OrderPositionResponse addOrderPosition(OrderPositionRequest orderPositionRequest){
//        Product product = productRepository.findById(orderPositionRequest.getProductId())
//                .orElseThrow(EntityNotFoundException::new);
//        order order = orderRepository.findById(orderPositionRequest.getOrderId())
//                .orElseThrow(EntityNotFoundException::new);
//        orderposition orderposition = new orderposition();
//        orderposition.setOrder(order);
//        orderposition.setProduct(product);
//        orderposition createdOrderPosition = orderPositionRepository.save(orderposition);
//        return modelMapper.map(createdOrderPosition, OrderPositionResponse.class);
//    }

//    public List<OrderPositionResponse> addOrderPositions(List<OrderPositionRequest> orderPositionRequests){
//        List<OrderPosition> orderPositions = new ArrayList<>();
//        for(OrderPositionRequest orderPositionRequest : orderPositionRequests){
//            Product product = productRepository.findById(orderPositionRequest.getProductId())
//                    .orElseThrow(EntityNotFoundException::new);
//            Order order = orderRepository.findById(orderPositionRequest.getOrderId())
//                    .orElseThrow(EntityNotFoundException::new);
//            OrderPosition orderPosition = new OrderPosition();
//            orderPosition.setOrder(order);
//            orderPosition.setProduct(product);
//            orderPosition.setQuantity(orderPositionRequest.getQuantity());
//            OrderPosition createdOrderPosition = orderPositionRepository.save(orderPosition);
//            orderPositions.add(createdOrderPosition);
//        }
//        return orderPositions.stream()
//                .map(orderPosition -> modelMapper.map(orderPosition, OrderPositionResponse.class))
//                .collect(Collectors.toList());
//    }

    public OrderResponse addOrder(OrderRequest orderRequest){
        Order order = new Order();
        order = modelMapper.map(orderRequest, Order.class);
        List<OrderPositionResponse> orderPositionResponses = new ArrayList<>();
        User user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(EntityNotFoundException::new);
        order.setUser(user);
        Order createdOrder = orderRepository.save(order);
        for(OrderPositionRequest orderPositionReq : orderRequest.getOrderPositionRequests()){
            Product product = productRepository.findById(orderPositionReq.getProductId())
                    .orElseThrow(EntityNotFoundException::new);
            OrderPosition orderPosition = new OrderPosition();
            orderPosition.setOrder(order);
            orderPosition.setProduct(product);
            orderPosition.setQuantity(orderPositionReq.getQuantity());
            OrderPosition createdOrderPosition = orderPositionRepository.save(orderPosition);
            orderPositionResponses.add(modelMapper.map(createdOrderPosition, OrderPositionResponse.class));
        }
        OrderResponse orderResponse = modelMapper.map(createdOrder, OrderResponse.class);
        orderResponse.setOrderPositionResponses(orderPositionResponses);
        return orderResponse;
    }




}
