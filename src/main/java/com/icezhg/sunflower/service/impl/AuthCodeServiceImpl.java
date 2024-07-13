package com.icezhg.sunflower.service.impl;

import com.icezhg.sunflower.common.Constant;
import com.icezhg.sunflower.domain.IpLocation;
import com.icezhg.sunflower.domain.Openid;
import com.icezhg.sunflower.security.UserDetail;
import com.icezhg.sunflower.service.AuthCodeService;
import com.icezhg.sunflower.service.IpLocationService;
import com.icezhg.sunflower.service.OpenidService;
import com.icezhg.sunflower.util.IPAddressUtil;
import com.icezhg.sunflower.util.IpUtil;
import com.icezhg.sunflower.util.Requests;
import com.icezhg.sunflower.visitor.WechatVisitor;
import com.icezhg.sunflower.visitor.pojo.WechatSession;
import org.springframework.beans.factory.annotation.Autowired;
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
            openid.setNickname("");
            // openid.setStatus(UserStatus.INCOMPLETE.getStatus());
            openid.setCreateTime(new Date());
            openid.setUpdateTime(new Date());
            openid.setRemark("");
            openidService.save(openid);
        }

        return UserDetail.builder()
                .id(String.valueOf(openid.getId()))
                .username(openid.getOpenid())
                .name("")
                .nickname(openid.getNickname())
                .createTime(String.valueOf(openid.getCreateTime().getTime()))
                .updateTime(String.valueOf(openid.getUpdateTime().getTime()))
                .authorities(Set.of(new SimpleGrantedAuthority("wx")))
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .attributes(attributeMap(wechatSession.getSessionKey()))
                .build();
    }

    private Map<String, String> attributeMap(String sessionKey) {
        String requestIp = IpUtil.getRequestIp();
        String ipLocation = findAndSaveIpLocation(requestIp);
        Map<String, String> attributes = new java.util.HashMap<>();
        attributes.put(Constant.ATTRIBUTE_IP, requestIp);
        attributes.put(Constant.ATTRIBUTE_IP_LOCATION, ipLocation);
        attributes.put(Constant.ATTRIBUTE_AGENT, Requests.userAgent());
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
