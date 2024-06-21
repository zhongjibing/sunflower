package com.icezhg.sunflower.security.captcha;

import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.core.Authentication;

/**
 * Created by zhongjibing on 2023/06/21.
 */
public class AuthenticationFailureBadCaptchaEvent extends AbstractAuthenticationFailureEvent{

    public AuthenticationFailureBadCaptchaEvent(Authentication authentication) {
        super(authentication, new BadCaptchaException("Bad captcha"));
    }
}
