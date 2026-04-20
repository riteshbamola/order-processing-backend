package com.ritesh.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonDTO {

    private String orderId;
    private String productId;
    private double amount;
    private int quantity;
    private OrderStatus orderStatus;
}





