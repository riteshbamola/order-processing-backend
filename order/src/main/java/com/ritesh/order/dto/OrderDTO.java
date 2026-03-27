package com.ritesh.order.dto;

import com.ritesh.order.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private String orderId;
    private String productId;

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderId='" + orderId + '\'' +
                ", productId='" + productId + '\'' +
                ", amount=" + amount +
                ", quantity=" + quantity +
                ", orderStatus=" + orderStatus +
                '}';
    }

    private double amount;
    private int quantity;
    private OrderStatus orderStatus;

    public static OrderDTO fromEntity(com.ritesh.order.model.OrderDetail order) {
        return OrderDTO.builder()
                .orderId(order.getOrderId())
                .productId(order.getProductId())
                .amount(order.getAmount())
                .quantity(order.getQuantity())
                .orderStatus(order.getOrderStatus())
                .build();
    }

    public com.ritesh.order.model.OrderDetail toEntity() {
        com.ritesh.order.model.OrderDetail order = new com.ritesh.order.model.OrderDetail();
        order.setOrderId(this.orderId);
        order.setProductId(this.productId);
        order.setAmount(this.amount);
        order.setQuantity(this.quantity);
        order.setOrderStatus(this.orderStatus);
        return order;
    }

}