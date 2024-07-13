package com.icezhg.sunflower.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.icezhg.sunflower.security.UserDetail;
import com.icezhg.sunflower.security.authentication.AuthCodeAuthenticationToken;
import com.icezhg.sunflower.serial.AuthCodeAuthenticationTokenMixin;
import com.icezhg.sunflower.serial.AuthenticationExceptionMixIn;
import com.icezhg.sunflower.serial.UserDetailMixIn;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.jackson2.SecurityJackson2Modules;

public class ObjectMapperFactory {

    public static ObjectMapper getObjectMapper() {
        return JsonMapper.builder()
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
                .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .enable(JsonParser.Feature.ALLOW_SINGLE_QUOTES)
                .enable(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES)
                .enable(SerializationFeature.WRITE_DATES_WITH_ZONE_ID)
                .addModules(SecurityJackson2Modules.getModules(null))
                .addMixIn(AuthenticationException.class, AuthenticationExceptionMixIn.class)
                .addMixIn(UserDetail.class, UserDetailMixIn.class)
                .addMixIn(AuthCodeAuthenticationToken.class, AuthCodeAuthenticationTokenMixin.class)
                .build();
    }
}
