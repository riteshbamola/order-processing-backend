package com.ritesh.payment.db;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "payment_id", nullable = false, updatable = false)
    private String paymentId;

    @Column(name = "order_id", nullable = false)
    private String orderId;

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod; // e.g. UPI, CARD, NET_BANKING

    @Column(name = "status", nullable = false)
    private PaymentSattus status = PaymentSattus.PENDING; // e.g. SUCCESS, FAILED, PENDING

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

}
