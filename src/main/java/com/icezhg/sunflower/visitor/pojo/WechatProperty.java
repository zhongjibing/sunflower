package com.icezhg.sunflower.visitor.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by zhongjibing on 2023/02/19.
 */
@ConfigurationProperties(prefix = "wechat")
public class WechatProperty {
    private static final String CODE2SESSION = "/sns/jscode2session" +
            "?appid={appid}&secret={secret}&js_code={code}&grant_type=authorization_code";

    private String appId;

    private String secretKey;

    private String urlPrefix = "https://api.weixin.qq.com";


    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public String getUrlPrefix() {
        return urlPrefix;
    }

    public void setUrlPrefix(String urlPrefix) {
        this.urlPrefix = urlPrefix;
    }

    public String getCode2sessionUrl() {
        return urlPrefix + CODE2SESSION;
    }
}
