package com.ritesh.order.service;

import com.ritesh.common.dto.CommonDTO;
import com.ritesh.order.kafka.KafkaProducer;
import com.ritesh.order.model.OrderDetail;
import com.ritesh.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private KafkaProducer kafkaProducer;

    public Map<String,String> addOrder(OrderDetail order){
        order = orderRepo.save(order);

        CommonDTO common = CommonDTO.builder().amount(order.getAmount()).orderId(order.getOrderId()).build();

    
        kafkaProducer.sendEvent(common,"ORDER_CREATED");
        return  Map.of(
                "message", "Order Created Succesfully"
        );
    }

    public  void updateOrder(String orderId,com.ritesh.common.dto.OrderStatus orderStatus) throws Exception{
        OrderDetail order = orderRepo.findById(orderId).orElseThrow(() -> new Exception(" Order Not Found"));
        order.setOrderStatus(orderStatus);





    }
}
