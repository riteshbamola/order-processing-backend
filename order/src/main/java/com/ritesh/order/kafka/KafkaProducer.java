package com.ritesh.order.kafka;

import com.ritesh.order.dto.OrderDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, OrderDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(OrderDTO orderDTO, String topic){
        kafkaTemplate.send(topic,orderDTO);
        //commit cdc log also later when i complete cdc implementation
    }
}
