package com.icezhg.sunflower.listener;


import com.icezhg.sunflower.security.UserDetail;
import com.icezhg.sunflower.service.LoginRecordService;
import com.icezhg.sunflower.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

/**
 * Created by zhongjibing on 2022/09/10.
 */
@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

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
        if (event.getAuthentication().getPrincipal() instanceof UserDetail userDetail) {
            userService.updateLastLoginTime(userDetail.getUsername());
            loginRecordService.saveLoginInfo(userDetail.getId(), userDetail.getUsername(), userDetail.getAttributes());
        }
    }
}
