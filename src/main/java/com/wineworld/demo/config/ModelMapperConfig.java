package com.wineworld.demo.config;

import com.wineworld.demo.dtos.opinion.OpinionRequest;
import com.wineworld.demo.dtos.opinion.OpinionResponse;
import com.wineworld.demo.dtos.order.OrderRequest;
import com.wineworld.demo.dtos.orderposition.OrderPositionRequest;
import com.wineworld.demo.dtos.orderposition.OrderPositionResponse;
import com.wineworld.demo.dtos.product.MiniProductResponse;
import com.wineworld.demo.dtos.product.ProductRequest;
import com.wineworld.demo.dtos.product.ProductResponse;
import com.wineworld.demo.dtos.user.UserRequest;
import com.wineworld.demo.entities.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class ModelMapperConfig {


    public static void getOrderPositionMapping(ModelMapper modelMapper){
        PropertyMap<OrderPosition, OrderPositionResponse> orderPositionMap = new PropertyMap<OrderPosition, OrderPositionResponse>() {
            @Override
            protected void configure() {
                map().setOrderId(source.getOrder().getOrderId());
                map().setProductId(source.getProduct().getProductId());
                map().setName(source.getProduct().getName());
                map().setPrice(source.getProduct().getPrice());
                map().setPictureUrl(source.getProduct().getPictureUrl());
                map().setProducer(source.getProduct().getProducer());
                map().setGenre(source.getProduct().getGenre().getName());
                map().setAlcoholLevel(source.getProduct().getAlcoholLevel());
                map().setYear(source.getProduct().getYear());
                map().setVolume(source.getProduct().getVolume());
                map().setQuantity(source.getQuantity());

            }
        };
        modelMapper.addMappings(orderPositionMap);
        PropertyMap<OrderRequest, Order> orderPropertyMap = new PropertyMap<OrderRequest, Order>() {
            @Override
            protected void configure() {
                skip(destination.getOrderPositions());
                skip(destination.getOrderId());
            }
        };
        modelMapper.addMappings(orderPropertyMap);
    }

    public static ModelMapper getOpinionMapping(){
        ModelMapper modelMapper = new ModelMapper();
        PropertyMap<Opinion, OpinionResponse> opinionMap = new PropertyMap<Opinion, OpinionResponse>() {
            @Override
            protected void configure() {
                map().setProductId(source.getProduct().getProductId());
                map().setUserId(source.getUser().getUserId());
                map().setLogin(source.getUser().getLogin());
                map().setProductName(source.getProduct().getName());
            }
        };
        modelMapper.addMappings(opinionMap);

        return modelMapper;
    }

    public static void addProductMappings(ModelMapper modelMapper){
        PropertyMap<OpinionRequest, Opinion> opinionRequestMap = new PropertyMap<OpinionRequest, Opinion>() {
            @Override
            protected void configure() {
                skip(destination.getOpinionId());
                map().setRating(source.getRating());
                map().setComment(source.getComment());
            }
        };
        modelMapper.addMappings(opinionRequestMap);
        PropertyMap<ProductRequest, Product> productMapping = new PropertyMap<ProductRequest, Product>() {
            @Override
            protected void configure() {
                skip(destination.getProductId());
                skip(destination.getPictureUrl());
            }
        };
        modelMapper.addMappings(productMapping);
        PropertyMap<Product, ProductResponse> productResponsePropertyMap = new PropertyMap<Product, ProductResponse>() {
            @Override
            protected void configure() {
                map().setProductDescription(source.getProductDescription());
                map().setDescription(source.getLocation().getDescription());
            }
        };
        modelMapper.addMappings(productResponsePropertyMap);
        PropertyMap<Product, MiniProductResponse> miniProductResponsePropertyMap = new PropertyMap<Product, MiniProductResponse>() {
            @Override
            protected void configure() {
                map().setProductDescription(source.getProductDescription());
            }
        };
        modelMapper.addMappings(miniProductResponsePropertyMap);
    }

    public static void addUserMappings(ModelMapper modelMapper){
        PropertyMap<UserRequest, User>  userPropertyMap = new PropertyMap<UserRequest, User>() {
            @Override
            protected void configure() {
                skip(destination.getUserId());
            }
        };
        modelMapper.addMappings(userPropertyMap);
    }

}
