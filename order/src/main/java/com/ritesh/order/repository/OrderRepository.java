package com.ritesh.order.repository;

import com.ritesh.order.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderDetail, String> {
}
