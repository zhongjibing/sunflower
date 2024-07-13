package com.icezhg.sunflower.service;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by zhongjibing on 2023/02/22.
 */
public interface AuthCodeService {
    UserDetails auth(String code);
}
