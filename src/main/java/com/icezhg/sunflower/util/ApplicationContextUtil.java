package com.icezhg.sunflower.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * Created by zhongjibing on 2019/07/26
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        Assert.notNull(ApplicationContextUtil.applicationContext, "applicationContext have not been initialized");
        return ApplicationContextUtil.applicationContext;
    }

    public static void publishEvent(@NonNull Object event) {
        getApplicationContext().publishEvent(event);
    }

    public static Object getBean(@NonNull String name) {
        return getApplicationContext().getBean(name);
    }

    public static <T> T getBean(@NonNull Class<T> beanType) {
        return getApplicationContext().getBean(beanType);
    }

    public static <T> T getBean(String name, @NonNull Class<T> beanType) {
        return getApplicationContext().getBean(name, beanType);
    }

    public static <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getApplicationContext().getBeansOfType(type);
    }
}
