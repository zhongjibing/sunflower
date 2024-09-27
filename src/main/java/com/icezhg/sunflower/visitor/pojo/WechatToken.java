package com.icezhg.sunflower.visitor.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by zhongjibing on 2023/08/26.
 */
public class WechatToken {

    @JsonProperty("errcode")
    private transient int errcode;
    @JsonProperty("expires_in")
    private transient long expiresIn;
    @JsonProperty("access_token")
    private transient String accessToken;

    public int getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode != null ? errcode : 0;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn != null ? expiresIn : 0L;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public boolean isValid() {
        return errcode == 0;
    }


}
