package com.wineworld.demo.config;

import com.wineworld.demo.dtos.Opinion.OpinionRequest;
import com.wineworld.demo.dtos.Opinion.OpinionResponse;
import com.wineworld.demo.dtos.OrderPosition.OrderPositionResponse;
import com.wineworld.demo.entities.Opinion;
import com.wineworld.demo.entities.OrderPosition;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class ModelMapperConfig {


    public static ModelMapper getOrderPositionMapping(){
        ModelMapper modelMapper = new ModelMapper();
        PropertyMap<OrderPosition, OrderPositionResponse> orderPositionMap = new PropertyMap<OrderPosition, OrderPositionResponse>() {
            @Override
            protected void configure() {
                map().setOrderId(source.getOrder().getOrderId());
                map().setProductId(source.getProduct().getProductId());
                map().setName(source.getProduct().getName());
                map().setPrice(source.getProduct().getPrice());
                map().setPictureUrl(source.getProduct().getPictureUrl());
                map().setProducer(source.getProduct().getProducer());
                map().setGenre(source.getProduct().getGenre());
                map().setAlcoholLevel(source.getProduct().getAlcoholLevel());
                map().setYear(source.getProduct().getYear());
                map().setVolume(source.getProduct().getVolume());
            }
        };
        modelMapper.addMappings(orderPositionMap);
        return modelMapper;
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
        PropertyMap<OpinionRequest, Opinion> opinionRequestMap = new PropertyMap<OpinionRequest, Opinion>() {
            @Override
            protected void configure() {
                skip(destination.getOpinionId());
                map().setRating(source.getRating());
                map().setComment(source.getComment());
            }
        };
        modelMapper.addMappings(opinionRequestMap);
        return modelMapper;
    }
}
