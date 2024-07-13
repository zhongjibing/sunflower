package com.icezhg.sunflower.visitor.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhongjibing on 2023/02/19.
 */
public class WechatSession {
    private transient String openid;
    @JsonProperty("session_key")
    private transient String sessionKey;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public boolean isValid() {
        return sessionKey != null && openid != null;
    }

    @Override
    public String toString() {
        return "WechatSession{" +
                "openid='" + openid + '\'' +
                ", sessionKey='" + sessionKey + '\'' +
                '}';
    }
}
