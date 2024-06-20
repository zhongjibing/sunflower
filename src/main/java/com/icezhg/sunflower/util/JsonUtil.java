package com.icezhg.sunflower.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.io.Serial;
import java.text.DateFormat;

/**
 * Created by zhongjibing on 2019/07/25
 */
public final class JsonUtil {

    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = ObjectMapperFactory.getObjectMapper();
    }

    private JsonUtil() {
    }

    public static String toJson(Object object) {
        try {
            return OBJECT_MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonParseException(e);
        }
    }

    public static String toJson(Object object, DateFormat dateFormat) {
        ObjectMapper mapper = ObjectMapperFactory.getObjectMapper();
        mapper.setDateFormat(dateFormat);

        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new JsonParseException(e);
        }
    }

    public static <T> T toBean(String json, Class<T> type) {
        if (json == null || json.isEmpty()) {
            return null;
        }

        try {
            return OBJECT_MAPPER.readValue(json, type);
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }

    public static <T> T toBean(String json, TypeReference<T> typeRef) {
        if (json == null || json.isEmpty()) {
            return null;
        }

        try {
            return OBJECT_MAPPER.readValue(json, typeRef);
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }

    public static <T> T toBean(InputStream in, Class<T> type) {
        try {
            return OBJECT_MAPPER.readValue(in, type);
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }

    public static <T> T toBean(InputStream in, TypeReference<T> typeRef) {
        try {
            return OBJECT_MAPPER.readValue(in, typeRef);
        } catch (Exception e) {
            throw new JsonParseException(e);
        }
    }

    private static class JsonParseException extends RuntimeException {
        @Serial
        private static final long serialVersionUID = -5271479650559751840L;

        JsonParseException(Throwable cause) {
            super(cause);
        }
    }
}
