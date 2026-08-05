package com.ritesh.saga_orchestrator.kafka;

import com.ritesh.common.dto.CommonDTO;

import com.ritesh.saga_orchestrator.service.SagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    private SagaService sagaService;

    public KafkaConsumer(SagaService sagaService){
        this.sagaService=sagaService;
    }

    @KafkaListener(topics = {
            "ORDER_CREATED",
            "ORDER_CANCELED",
            "INVENTORY_RESERVED",
            "INVENTORY_RELEASED",
            "INVENTORY.FAILED",
            "PAYMENT_SUCCESS",
            "PAYMENT_FAILED"
    })
    public void handleEvents(CommonDTO event,
                             @Header(KafkaHeaders.RECEIVED_TOPIC) String topic,
                             Acknowledgment acknowledgment) {

        try {
            switch (topic) {

                case "ORDER_CREATED":
                    sagaService.reserveInventory(event);
                    break;

                case "INVENTORY_RESERVED":
                    sagaService.startPayment(event);
                    break;

                case "INVENTORY_FAILED":
                    sagaService.cancelOrder(event);
                    break;

                case "PAYMENT_SUCCESS":
                    sagaService.completeOrder(event);
                    break;

                case "PAYMENT_FAILED":
                    sagaService.releaseInventory(event);
                    break;

                case "INVENTORY_RELEASED":
                    sagaService.cancelOrder(event);
                    break;

                default:
                    System.out.println("Unknown topic: " + topic);
            }

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

        acknowledgment.acknowledge();
    }

}
