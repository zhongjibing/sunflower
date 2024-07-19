package com.icezhg.sunflower.listener;


import com.icezhg.sunflower.common.Constant;
import com.icezhg.sunflower.domain.Session;
import com.icezhg.sunflower.security.UserDetail;
import com.icezhg.sunflower.service.SessionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.web.authentication.session.SessionFixationProtectionEvent;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by zhongjibing on 2022/09/10.
 */
@Component
public class SessionFixationProtectionEventListener implements ApplicationListener<SessionFixationProtectionEvent> {
    private static final Logger log = LoggerFactory.getLogger(SessionFixationProtectionEventListener.class);

    private SessionService sessionService;

    @Autowired
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public void onApplicationEvent(SessionFixationProtectionEvent event) {
        Object principal = event.getAuthentication().getPrincipal();
        log.info("session fixation protection: {} | {}", principal, principal.getClass());
        if (principal instanceof UserDetail userDetail) {
            Session session = new Session();
            session.setOldSessionId(event.getOldSessionId());
            session.setNewSessionId(event.getNewSessionId());
            session.setUserId(userDetail.getId());
            session.setUsername(userDetail.getUsername());
            session.setName(userDetail.getName());
            session.setNickname(userDetail.getNickname());
            session.setAvatar(userDetail.getAvatar());
            session.setLoginIp(userDetail.getAttributes().get(Constant.ATTRIBUTE_IP));
            session.setLoginLocation(userDetail.getAttributes().get(Constant.ATTRIBUTE_IP_LOCATION));
            session.setAgent(userDetail.getAttributes().get(Constant.ATTRIBUTE_AGENT));
            session.setLoginTime(new Date());
            session.setLastAccessedTime(new Date());

            sessionService.save(session);
        }
    }
}
