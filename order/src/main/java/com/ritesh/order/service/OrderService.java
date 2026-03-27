package com.ritesh.order.service;

import com.ritesh.order.enums.OrderStatus;
import com.ritesh.order.model.Order;
import com.ritesh.order.repository.OrderRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {

    private OrderRepository orderRepo;


    public Map<String,String> addOrder(Order order){
        orderRepo.save(order);
        return  Map.of(
                "message", "Order Created Succesfully"
        );
    }

    public  void updateOrder(String orderId,OrderStatus orderStatus) throws Exception{
        Order order = orderRepo.findById(orderId).orElseThrow(() -> new Exception(" Order Not Found"));
        order.setOrderStatus(orderStatus);
    }
}
