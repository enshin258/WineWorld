package com.wineworld.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Orders")
@Data
@RequiredArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @NonNull
    private String orderDate;

    @NonNull
    private String orderAddress;

    @NonNull
    private String orderCity;

    @NonNull
    private String orderPostalCode;

    @NonNull
    private float orderTotalCost;

    @ManyToOne
    @JsonIgnore
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderPosition> orderPositions;

}
