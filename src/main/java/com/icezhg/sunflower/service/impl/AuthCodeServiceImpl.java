package com.icezhg.sunflower.service.impl;

import com.icezhg.sunflower.common.Authority;
import com.icezhg.sunflower.common.Constant;
import com.icezhg.sunflower.domain.IpLocation;
import com.icezhg.sunflower.domain.Openid;
import com.icezhg.sunflower.enums.LoginMethod;
import com.icezhg.sunflower.enums.UserStatus;
import com.icezhg.sunflower.enums.WxRole;
import com.icezhg.sunflower.security.UserDetail;
import com.icezhg.sunflower.service.AuthCodeService;
import com.icezhg.sunflower.service.IpLocationService;
import com.icezhg.sunflower.service.OpenidService;
import com.icezhg.sunflower.util.IPAddressUtil;
import com.icezhg.sunflower.util.IpUtil;
import com.icezhg.sunflower.util.Requests;
import com.icezhg.sunflower.visitor.WechatVisitor;
import com.icezhg.sunflower.visitor.pojo.WechatSession;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhongjibing on 2023/02/22.
 */
@Service
public class AuthCodeServiceImpl implements AuthCodeService {

    private OpenidService openidService;

    private IpLocationService ipLocationService;

    private WechatVisitor wechatVisitor;

    @Autowired
    public void setOpenidService(OpenidService openidService) {
        this.openidService = openidService;
    }

    @Autowired
    public void setIpLocationService(IpLocationService ipLocationService) {
        this.ipLocationService = ipLocationService;
    }

    @Autowired
    public void setWechatVisitor(WechatVisitor wechatVisitor) {
        this.wechatVisitor = wechatVisitor;
    }

    @Override
    public UserDetails auth(String code) {
        WechatSession wechatSession = wechatVisitor.code2session(code);
        if (!wechatSession.isValid()) {
            return null;
        }

        Openid openid = openidService.findByOpenid(wechatSession.getOpenid());
        if (openid == null) {
            openid = new Openid();
            openid.setOpenid(wechatSession.getOpenid());
            openid.setRole(WxRole.USER.getRole());
            openid.setStatus(UserStatus.NORMAL.getStatus());
            openid.setCreateTime(new Date());
            openid.setUpdateTime(new Date());
            openidService.save(openid);
        }

        return UserDetail.builder()
                .id(String.valueOf(openid.getId()))
                .username(openid.getOpenid())
                .openid(openid.getOpenid())
                .name(openid.getNickname())
                .nickname(openid.getNickname())
                .createTime(String.valueOf(openid.getCreateTime().getTime()))
                .updateTime(String.valueOf(openid.getUpdateTime().getTime()))
                .authorities(authorities(openid.getRole()))
                .accountNonExpired(true)
                .accountNonLocked(StringUtils.equals(openid.getStatus(), UserStatus.NORMAL.getStatus()))
                .credentialsNonExpired(true)
                .attributes(attributeMap(code, wechatSession.getSessionKey()))
                .loginMethod(LoginMethod.WX.getMethod())
                .build();
    }

    private Set<GrantedAuthority> authorities(Integer role) {
        WxRole wxRole = WxRole.roleOf(role);
        if (wxRole == WxRole.USER) {
            return Set.of(new SimpleGrantedAuthority(Authority.Wx.USER));
        } else if (wxRole == WxRole.ADMIN) {
            return Set.of(new SimpleGrantedAuthority(Authority.Wx.ADMIN));
        } else {
            return Set.of();
        }
    }

    private Map<String, String> attributeMap(String authCode, String sessionKey) {
        String requestIp = IpUtil.getRequestIp();
        String ipLocation = findAndSaveIpLocation(requestIp);
        Map<String, String> attributes = new java.util.HashMap<>();
        attributes.put(Constant.ATTRIBUTE_IP, requestIp);
        attributes.put(Constant.ATTRIBUTE_IP_LOCATION, ipLocation);
        attributes.put(Constant.ATTRIBUTE_AGENT, Requests.userAgent());
        attributes.put(Constant.AUTH_CODE, authCode);
        attributes.put(Constant.SESSION_KEY, sessionKey);
        return attributes;
    }

    private String findAndSaveIpLocation(String ip) {
        IpLocation ipLocation = ipLocationService.findByIp(ip);
        if (ipLocation != null) {
            return ipLocation.getLocation();
        }

        String location = IPAddressUtil.getLocation(ip);
        ipLocationService.save(new IpLocation(ip, location));
        return location;
    }


}
