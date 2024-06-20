package com.icezhg.sunflower.service;

import com.icezhg.sunflower.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * Created by zhongjibing on 2023/06/20.
 */
public interface UserService extends UserDetailsService {

    User findUserByUsername(String username);

    void updateLastLoginTime(String username);
}
