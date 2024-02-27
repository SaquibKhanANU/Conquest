package com.Game.conquest.server.converter;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GenericConverter<T> {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    // Private constructor to prevent instantiation
    private GenericConverter() {
        throw new AssertionError(); // Ensure that this class is never instantiated
    }

    // Convert JSON string to object of type T
    public static <T> T fromJson(String json, Class<T> valueType) throws Exception {
        return objectMapper.readValue(json, valueType);
    }

    // Convert object of type T to JSON string
    public static <T> String toJson(T object) throws Exception {
        return objectMapper.writeValueAsString(object);
    }
}
