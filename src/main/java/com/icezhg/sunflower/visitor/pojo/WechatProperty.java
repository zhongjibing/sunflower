package com.icezhg.sunflower.visitor.pojo;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by zhongjibing on 2023/02/19.
 */
@ConfigurationProperties(prefix = "wechat")
public class WechatProperty {
    private static final String CODE2SESSION = "/sns/jscode2session" +
            "?appid={appid}&secret={secret}&js_code={code}&grant_type=authorization_code";

    private static final String TOKEN = "/cgi-bin/token?grant_type=client_credential&appid={appid}&secret={secret}";

    private static final String MESSAGE_SEND = "/cgi-bin/message/subscribe/send?access_token={token}";

    private String appId;

    private String secretKey;

    private String urlPrefix = "https://api.weixin.qq.com";

    private Subscribe subscribe;


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

    public String getTokenUrl() {
        return urlPrefix + TOKEN;
    }

    public String getMessageSendUrl() {
        return urlPrefix + MESSAGE_SEND;
    }

    public void setSubscribe(Subscribe subscribe) {
        this.subscribe = subscribe;
    }

    public String getSubscribeNotice() {
        return subscribe != null ? subscribe.getNotice() : null;
    }

    public String getSubscribeConfirm() {
        return subscribe != null ? subscribe.getConfirm() : null;
    }


    public static class Subscribe {
        private String notice;
        private String confirm;

        public String getNotice() {
            return notice;
        }

        public void setNotice(String notice) {
            this.notice = notice;
        }

        public String getConfirm() {
            return confirm;
        }

        public void setConfirm(String confirm) {
            this.confirm = confirm;
        }
    }
}
