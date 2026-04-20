package com.ritesh.order.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ritesh.common.dto.CommonDTO;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;  // ✅ Kafka's, not Spring's

import java.io.IOException;

public class MyDeserializer implements Deserializer<CommonDTO> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public CommonDTO deserialize(String topic, byte[] value) {
        if (value == null) {
            return null;
        }

        try {
            return objectMapper.readValue(value, CommonDTO.class);
        } catch (IOException | RuntimeException e) {
            throw new SerializationException("Error deserializing OrderDTO", e);
        }
    }

    @Override
    public void close() {
        // no-op
    }
}