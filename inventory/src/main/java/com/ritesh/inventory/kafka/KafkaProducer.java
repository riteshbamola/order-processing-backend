package com.ritesh.inventory.kafka;

import com.ritesh.common.dto.CommonDTO;
import org.springframework.kafka.core.KafkaTemplate;

public class KafkaProducer {

    private KafkaTemplate<String, CommonDTO> kafkaTemplate;

    public void sendMessage(CommonDTO commonDTO, String topic){
        kafkaTemplate.send(topic,commonDTO.getOrderId(), commonDTO);
    }

}
