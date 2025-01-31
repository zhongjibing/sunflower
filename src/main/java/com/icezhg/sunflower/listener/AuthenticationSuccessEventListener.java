package com.icezhg.sunflower.listener;


import com.icezhg.sunflower.enums.LoginMethod;
import com.icezhg.sunflower.security.UserDetail;
import com.icezhg.sunflower.service.LoginRecordService;
import com.icezhg.sunflower.service.OpenidService;
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

    private OpenidService openidService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setLoginRecordService(LoginRecordService loginRecordService) {
        this.loginRecordService = loginRecordService;
    }

    @Autowired
    public void setOpenidService(OpenidService openidService) {
        this.openidService = openidService;
    }

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        Object principal = event.getAuthentication().getPrincipal();
        if (principal instanceof UserDetail userDetail) {
            if (userDetail.getLoginMethod() == LoginMethod.WEB.getMethod()) {
                userService.updateLastLoginTime(userDetail.getUsername());
            } else if (userDetail.getLoginMethod() == LoginMethod.WX.getMethod()) {
                openidService.updateLastLoginTime(userDetail.getUsername());
            }
            loginRecordService.saveLoginInfo(userDetail);
        }
    }
}
