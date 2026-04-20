package com.ritesh.saga_orchestrator.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SagaRepo extends JpaRepository<Saga, String> {
}
