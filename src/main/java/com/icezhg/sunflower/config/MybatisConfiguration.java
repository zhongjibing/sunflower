package com.icezhg.sunflower.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by zhongjibing on 2020/03/15
 */
@MapperScan("com.icezhg.sunflower.dao")
@Configuration(proxyBeanMethods = false)
public class MybatisConfiguration {
}
