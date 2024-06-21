package com.icezhg.sunflower.security.captcha;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by zhongjibing on 2023/06/21.
 */
public class BadCaptchaException extends AuthenticationException {

    public BadCaptchaException(String msg) {
        super(msg);
    }
}
