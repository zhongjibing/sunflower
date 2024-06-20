package com.icezhg.sunflower.controller;

import com.icezhg.sunflower.pojo.UserDetail;
import com.icezhg.sunflower.pojo.UserInfo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhongjibing on 2023/06/20.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/info")
    public Object userInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetail userDetail) {
            return UserInfo.builder()
                    .id(userDetail.getId())
                    .name(userDetail.getName())
                    .nickname(userDetail.getNickname())
                    .gender(userDetail.getGender())
                    .birthdate(userDetail.getBirthdate())
                    .picture(userDetail.getAvatar())
                    .email(userDetail.getEmail())
                    .mobile(userDetail.getMobile())
                    .createTime(userDetail.getCreateTime())
                    .updateTime(userDetail.getUpdateTime())
                    .authorities(userDetail.getAuthorities().stream().map(GrantedAuthority::getAuthority).toList())
                    .build();
        }
       return null;
    }
}
