package com.icezhg.sunflower.serial;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Created by zhongjibing on 2022/09/11.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"cause", "stackTrace"})
public class AuthenticationExceptionMixIn {

    @JsonCreator
    AuthenticationExceptionMixIn(@JsonProperty("message") String message) {
    }
}
