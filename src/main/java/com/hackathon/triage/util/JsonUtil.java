package com.hackathon.triage.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;

/**
 * @author <a href="mailto:arpit.srivastava@navis.com">Arpit Srivastava</a>
 */
public class JsonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        mapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static <T> T fromJson(String inJson, TypeReference<T> inTypeReference) {
        T t = null;
        try {
            t = getMapper().readValue(inJson, new TypeReference<T>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static String toJson(Object inValue) {
        String s = null;
        try {
            s = getMapper().writeValueAsString(inValue);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return s;
    }
}
