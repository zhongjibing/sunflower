package com.icezhg.sunflower.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.stereotype.Component;

/**
 * Created by zhongjibing on 2022/12/14.
 */
@Component
public class AuthenticationFailureEventListener implements ApplicationListener<AbstractAuthenticationFailureEvent> {


    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
    }
}
