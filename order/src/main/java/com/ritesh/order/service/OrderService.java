package com.ritesh.order.service;

import com.ritesh.order.dto.OrderDTO;
import com.ritesh.order.enums.OrderStatus;
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
        orderRepo.save(order);



        //testing purpose;
        OrderDTO orderDTO = OrderDTO.fromEntity(order);
        kafkaProducer.sendEvent(orderDTO,"order.test");
        return  Map.of(
                "message", "Order Created Succesfully"
        );
    }

    public  void updateOrder(String orderId,OrderStatus orderStatus) throws Exception{
        OrderDetail order = orderRepo.findById(orderId).orElseThrow(() -> new Exception(" Order Not Found"));
        order.setOrderStatus(orderStatus);





    }
}
