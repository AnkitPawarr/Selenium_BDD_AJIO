package com.Jio.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class ReadJSON {

    private ObjectMapper objectMapper = new ObjectMapper();

    public <T> T deserialize(String jsonPath, Class<T> targetClass) {
        try {
            File file = new File(jsonPath);
            return objectMapper.readValue(file, targetClass);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}