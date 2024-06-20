package com.icezhg.sunflower.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * Created by zhongjibing on 2022/09/10.
 */
@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {


    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
    }
}
