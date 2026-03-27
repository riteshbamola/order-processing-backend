package com.ritesh.order.model;


import com.ritesh.order.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class OrderDetail {

    @Id
    @Column(name="order_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderId;

    @Column(name="product_id")
    private String productId;

    @Column(name = "amount")
    private double amount;

    @Column(name= "quantity")
    private int quantity;

    @Column(name ="order_status")
    private OrderStatus orderStatus = OrderStatus.PENDING;


    @Column(name = "created_at")
    private Instant createdAt = Instant.now();

}
