package com.icezhg.sunflower.controller;

import com.icezhg.sunflower.util.SecurityUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhongjibing on 2021/10/15
 */
@RestController
public class AuthController {

    @RequestMapping("/authenticated")
    public Object authenticated() {
        return SecurityUtil.authenticated();
    }

    @GetMapping("/user/info")
    public Object userInfo() {
        return SecurityUtil.currentUserInfo();
    }
}
