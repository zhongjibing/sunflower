package com.icezhg.sunflower.visitor;

import com.icezhg.sunflower.visitor.pojo.WechatProperty;
import com.icezhg.sunflower.visitor.pojo.WechatSession;
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

    private RestTemplate restTemplate;

    private WechatProperty property;


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
}
