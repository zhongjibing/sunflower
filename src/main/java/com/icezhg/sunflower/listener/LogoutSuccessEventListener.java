package com.icezhg.sunflower.listener;


import com.icezhg.sunflower.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.LogoutSuccessEvent;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * Created by zhongjibing on 2022/09/10.
 */
@Component
public class LogoutSuccessEventListener implements ApplicationListener<LogoutSuccessEvent> {

    private SessionService sessionService;


    @Autowired
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public void onApplicationEvent(LogoutSuccessEvent event) {
        if (event.getAuthentication().getDetails() instanceof WebAuthenticationDetails details) {
            sessionService.deleteBySessionId(details.getSessionId());
        }
    }
}
