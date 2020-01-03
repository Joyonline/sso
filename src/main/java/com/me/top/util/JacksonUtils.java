package com.me.top.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JacksonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String objToString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }

    public static Map<String,Object> stringToMap(Object string) {
        try {
            return objectMapper.readValue(String.valueOf(string), Map.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
