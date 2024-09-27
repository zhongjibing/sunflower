package com.icezhg.sunflower.visitor;

import com.icezhg.commons.cache.MemCache;
import com.icezhg.sunflower.visitor.pojo.WechatMessage;
import com.icezhg.sunflower.visitor.pojo.WechatMessageData;
import com.icezhg.sunflower.visitor.pojo.WechatProperty;
import com.icezhg.sunflower.visitor.pojo.WechatResp;
import com.icezhg.sunflower.visitor.pojo.WechatSession;
import com.icezhg.sunflower.visitor.pojo.WechatToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by zhongjibing on 2023/02/19.
 */
@Component
@EnableConfigurationProperties(WechatProperty.class)
public class WechatVisitor {
    private static final Logger log = LoggerFactory.getLogger(WechatVisitor.class);

    private final MemCache<WechatToken> tokenCache;

    private RestTemplate restTemplate;

    private WechatProperty property;

    public WechatVisitor() {
        this.tokenCache = new MemCache<>(this::doGetWechatToken, (2 * 60 - 1) * 60 * 1000L);
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setWechatProperty(WechatProperty property) {
        this.property = property;
    }

    public WechatSession code2session(String code) {
        Map<String, String> map = Map.of("appid", property.getAppId(), "secret", property.getSecretKey(), "code", code);
        return restTemplate.getForObject(property.getCode2sessionUrl(), WechatSession.class, map);
    }

    private WechatToken doGetWechatToken() {
        Map<String, String> map = Map.of("appid", property.getAppId(), "secret", property.getSecretKey());
        return restTemplate.getForObject(property.getTokenUrl(), WechatToken.class, map);
    }

    public WechatToken token() {
        return tokenCache.getData();
    }


    private WechatResp sendMessage(String templateId, String toUser, WechatMessageData msgData) {
        Map<String, String> map = Map.of("token", token().getAccessToken());
        WechatMessage message = new WechatMessage(templateId, toUser, msgData);
        return restTemplate.postForObject(property.getMessageSendUrl(), message, WechatResp.class, map);
    }

    public void sendNoticeMessage(String toUser, WechatMessageData data) {
        WechatToken token = token();
        if (!token.isValid()) {
            log.error("send notice message error: {}", token.getErrcode());
            return;
        }

        WechatResp resp = sendMessage(property.getSubscribeNotice(), toUser, data);
        log.info("send notice message resp: {}", resp);
    }
}
