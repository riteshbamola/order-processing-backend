package com.ritesh.payment.kafka;

import com.ritesh.common.dto.CommonDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    private KafkaTemplate<String, CommonDTO> kafkaTemplate;

    public void sendMessage(CommonDTO commonDTO, String topic){
        kafkaTemplate.send(topic,commonDTO.getOrderId(), commonDTO);
    }

}
