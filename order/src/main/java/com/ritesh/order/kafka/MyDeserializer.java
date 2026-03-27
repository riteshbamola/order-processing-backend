package com.ritesh.order.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ritesh.order.dto.OrderDTO;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;  // ✅ Kafka's, not Spring's

import java.io.IOException;

public class MyDeserializer implements Deserializer<OrderDTO> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public OrderDTO deserialize(String topic, byte[] value) {
        if (value == null) {
            return null;
        }

        try {
            return objectMapper.readValue(value, OrderDTO.class);
        } catch (IOException | RuntimeException e) {
            throw new SerializationException("Error deserializing OrderDTO", e);
        }
    }

    @Override
    public void close() {
        // no-op
    }
}