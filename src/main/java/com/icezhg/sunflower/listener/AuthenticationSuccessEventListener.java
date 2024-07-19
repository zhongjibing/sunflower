package com.icezhg.sunflower.listener;


import com.icezhg.sunflower.enums.LoginMethod;
import com.icezhg.sunflower.security.UserDetail;
import com.icezhg.sunflower.service.LoginRecordService;
import com.icezhg.sunflower.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * Created by zhongjibing on 2022/09/10.
 */
@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationSuccessEventListener.class);


    private UserService userService;

    private LoginRecordService loginRecordService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setLoginRecordService(LoginRecordService loginRecordService) {
        this.loginRecordService = loginRecordService;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Object principal = event.getAuthentication().getPrincipal();
        if (principal instanceof UserDetail userDetail) {
            log.info("authentication success: {} | {}", userDetail.getId(), userDetail.getUsername());
            if (userDetail.getLoginMethod() == LoginMethod.WEB.getMethod()) {
                userService.updateLastLoginTime(userDetail.getUsername());
            }
            loginRecordService.saveLoginInfo(userDetail);
        }
    }
}
