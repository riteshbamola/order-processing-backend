package com.ritesh.order.kafka;

import com.ritesh.order.dto.OrderDTO;
import org.springframework.data.domain.jaxb.SpringDataJaxb;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaProducer {

    private final KafkaTemplate<String, OrderDTO> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, OrderDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(OrderDTO orderDTO){
        kafkaTemplate.send("order.created",orderDTO);


        //commit cdc log also later when i complete cdc implementation

    }
}
