package com.ritesh.order.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ritesh.order.dto.OrderDTO;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;  // ✅ Kafka's Serializer, not Spring's

import java.io.IOException;

public class MySerializer implements Serializer<OrderDTO> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, OrderDTO message) {  // ✅ Kafka requires topic param
        if (message == null) {
            return null;
        }

        try {
            return objectMapper.writeValueAsBytes(message);
        } catch (IOException | RuntimeException e) {
            throw new SerializationException("Error serializing OrderDTO", e);
        }
    }

    @Override
    public void close() {

    }
}