package com.icezhg.sunflower.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

/**
 * Created by zhongjibing on 2023/06/17.
 */
@EnableRedisHttpSession
@Configuration(proxyBeanMethods = false)
public class SessionConfiguration {
}
