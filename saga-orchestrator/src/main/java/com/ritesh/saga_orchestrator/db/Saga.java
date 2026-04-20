package com.ritesh.saga_orchestrator.db;

import com.ritesh.saga_orchestrator.enums.SagaStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Saga {


    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "saga_id", nullable = false, updatable = false)
    private String sagaId;

    @Column(name = "order_id", nullable = false)
    private String orderId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private SagaStatus status; // STARTED, IN_PROGRESS, COMPLETED, FAILED, COMPENSATED

    @Column(name = "current_step")
    private String currentStep; // ORDER_CREATED, INVENTORY_RESERVED, PAYMENT_DONE

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    private Instant updatedAt;


}
