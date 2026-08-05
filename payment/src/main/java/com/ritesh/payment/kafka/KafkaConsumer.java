package com.ritesh.payment.kafka;

import com.ritesh.common.dto.CommonDTO;

import com.ritesh.payment.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.AclEntry;

@Service
public class KafkaConsumer {

    @Autowired
    private PaymentService service;
    @KafkaListener(topics = "PAYMENT_START")
    public void reserveInventory(CommonDTO event, Acknowledgment acknowledgment){
        try {
            service.startPayment(event);

            //CDC event to orchestrator == inventory.reserved

        }catch (Exception e){
            System.out.println(e.getMessage());

            //CDC event to Orchestrator //Not found or Invenotry
        }
        acknowledgment.acknowledge();
    }


}
