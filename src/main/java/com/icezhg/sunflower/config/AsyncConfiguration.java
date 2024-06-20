package com.icezhg.sunflower.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.ForkJoinPool;

/**
 * Created by zhongjibing on 2022/12/24.
 */
@EnableAsync
@Configuration(proxyBeanMethods = false)
public class AsyncConfiguration {

    @Bean
    public Executor commonPool() {
        return new ForkJoinPool();
    }
}
