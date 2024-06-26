package com.icezhg.sunflower.pojo;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY, getterVisibility = JsonAutoDetect.Visibility.NONE,
        isGetterVisibility = JsonAutoDetect.Visibility.NONE)
@JsonIgnoreProperties(ignoreUnknown = true)
public class OnlineUser {
    private String id;
    private String sessionId;
    private String userId;
    private String username;
    private String name;
    private String loginIp;
    private String loginLocation;
    private String picture;
    private String nickname;
    private String lastAccessedTime;
    private String loginTime;
    private String userAgent;
    private String browser;
    private String os;
    private boolean currentSession;
}
