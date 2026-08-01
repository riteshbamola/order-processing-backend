package com.ritesh.saga_orchestrator.db;

import com.ritesh.saga_orchestrator.enums.SagaStepName;
import com.ritesh.saga_orchestrator.enums.StepStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;


@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SagaStep {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "step_id", nullable = false, updatable = false)
    private String stepId;

    @Column(name = "saga_id", nullable = false)
    private String sagaId;

    @Enumerated(EnumType.STRING)
    @Column(name = "step_name", nullable = false)
    private SagaStepName stepName;


    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private StepStatus status;


    @Column(name = "retry_count")
    private int retryCount;

    @Column(name = "error_message")
    private String errorMessage;

    @Column(name = "created_at", nullable = false)
    private Instant createdAt = Instant.now();

    @Column(name = "updated_at")
    private Instant updatedAt;


}
