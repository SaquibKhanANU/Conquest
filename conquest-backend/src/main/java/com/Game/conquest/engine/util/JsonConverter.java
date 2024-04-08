package com.Game.conquest.engine.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonConverter<T> {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static <T> T fromJson(String filePath, Class<T> type) throws IOException {
        File file = new File(filePath);
        return objectMapper.readValue(file, type);
    }
}
