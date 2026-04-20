package com.ritesh.order.kafka;

import com.ritesh.common.dto.CommonDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {

    private final KafkaTemplate<String, CommonDTO> kafkaTemplate;

    public KafkaProducer(KafkaTemplate<String, CommonDTO> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendEvent(CommonDTO orderDTO, String topic){
        kafkaTemplate.send(topic,orderDTO);
        //commit cdc log also later when i complete cdc implementation
    }
}
