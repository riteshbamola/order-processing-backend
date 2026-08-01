package com.ritesh.order.kafka;


import com.ritesh.common.dto.CommonDTO;
import com.ritesh.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.util.EventListener;

@Service
public class KafkaConsumer {


    @Autowired
    private OrderService orderService;

    @KafkaListener(topics = "ORDER.CANCEL")
    public void cancelOrder(CommonDTO order, Acknowledgment acknowledgment){
        try {
            orderService.updateOrder(order.getOrderId(), order.getOrderStatus());
            acknowledgment.acknowledge();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @KafkaListener(topics = "order.test")
    public  void testOrder(CommonDTO order, Acknowledgment acknowledgment){

        System.out.println("Order Event Received");
        System.out.println(order);
        acknowledgment.acknowledge();
    }
}
