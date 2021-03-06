package com.wineworld.demo.entities;

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
    private String date;

    @NonNull
    private String addressLineOne;

    @NonNull
    private String addressLineTwo;

    @NonNull
    private String city;

    @NonNull
    private String postalCode;

    @NonNull
    private String countryCode;

    @NonNull
    private float totalCost;

    @NonNull
    private String phoneNumber;

    @NonNull
    private String status;

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
    private List<OrderPosition> orderPositions;

}
