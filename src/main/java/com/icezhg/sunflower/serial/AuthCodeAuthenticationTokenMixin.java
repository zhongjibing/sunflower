package com.icezhg.sunflower.serial;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * Created by zhongjibing on 2023/02/23.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSerialize(using = AuthCodeAuthenticationTokenSerializer.class)
@JsonDeserialize(using = AuthCodeAuthenticationTokenDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface AuthCodeAuthenticationTokenMixin {
}
