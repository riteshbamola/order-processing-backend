package com.ritesh.payment.kafka;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ritesh.common.dto.CommonDTO;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.io.IOException;

public class MySerializer implements Serializer<CommonDTO> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public byte[] serialize(String topic, CommonDTO message) {
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