package com.icezhg.sunflower.service;

import com.icezhg.sunflower.domain.User;
import com.icezhg.sunflower.pojo.UserInfo;
import com.icezhg.sunflower.pojo.UserPasswd;
import com.icezhg.sunflower.pojo.UserStatus;
import com.icezhg.sunflower.pojo.query.Query;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Created by zhongjibing on 2023/06/20.
 */
public interface UserService extends UserDetailsService {

    User findUserByUsername(String username);

    void updateLastLoginTime(String username);

    boolean checkUnique(UserInfo userInfo);

    UserInfo save(UserInfo userInfo);

    UserInfo update(UserInfo userInfo);

    int count(Query query);

    List<UserInfo> find(Query query);

    int changeStatus(UserStatus userStatus);

    UserInfo findById(Long userId);

    int resetPasswd(UserPasswd userPasswd);
}
