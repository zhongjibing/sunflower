package com.icezhg.sunflower.serial;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonSerialize(using = UserDetailSerializer.class)
@JsonDeserialize(using = UserDetailDeserializer.class)
@JsonIgnoreProperties(ignoreUnknown = true)
public interface UserDetailMixIn {
}
