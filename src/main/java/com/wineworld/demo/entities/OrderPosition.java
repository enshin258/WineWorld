package com.wineworld.demo.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "OrderPositions")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderPosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderPositionId;

    @NonNull
    @ManyToOne
    private Order order;

    @NonNull
    @ManyToOne
    private Product product;

    @NonNull
    private int quantity;

}
