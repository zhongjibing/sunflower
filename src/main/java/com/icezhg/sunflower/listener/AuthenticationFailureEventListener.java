package com.icezhg.sunflower.listener;


import com.icezhg.sunflower.common.Constant;
import com.icezhg.sunflower.entity.IpLocation;
import com.icezhg.sunflower.entity.User;
import com.icezhg.sunflower.repository.IpLocationRepository;
import com.icezhg.sunflower.service.LoginRecordService;
import com.icezhg.sunflower.service.UserService;
import com.icezhg.sunflower.util.IPAddressUtil;
import com.icezhg.sunflower.util.IpUtil;
import com.icezhg.sunflower.util.Requests;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * Created by zhongjibing on 2022/12/14.
 */
@Component
public class AuthenticationFailureEventListener implements ApplicationListener<AbstractAuthenticationFailureEvent> {

    private UserService userService;

    private LoginRecordService loginRecordService;

    private IpLocationRepository ipLocationRepository;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setLoginInfoService(LoginRecordService loginRecordService) {
        this.loginRecordService = loginRecordService;
    }

    @Autowired
    public void setIpLocationRepository(IpLocationRepository ipLocationRepository) {
        this.ipLocationRepository = ipLocationRepository;
    }

    @Override
    public void onApplicationEvent(AbstractAuthenticationFailureEvent event) {
        if (event.getAuthentication() instanceof UsernamePasswordAuthenticationToken authenticationToken) {
            if (authenticationToken.getPrincipal() instanceof String username) {
                Map<String, String> attribute = attributeMap();
                Long userId = Optional.ofNullable(userService.findUserByUsername(username)).map(User::getId).orElse(-1L);
                loginRecordService.saveLoginInfo(userId, username, Constant.LOGIN_FAILURE, event.getException().getMessage(), attribute);
            }
        }
    }

    private Map<String, String> attributeMap() {
        String requestIp = IpUtil.getRequestIp();
        String ipLocation = findAndSaveIpLocation(requestIp);
        return Map.of(Constant.ATTRIBUTE_IP, requestIp, Constant.ATTRIBUTE_IP_LOCATION, ipLocation,
                Constant.ATTRIBUTE_AGENT, Requests.userAgent());
    }

    private String findAndSaveIpLocation(String ip) {
        IpLocation ipLocation = ipLocationRepository.findByIp(ip);
        if (ipLocation != null) {
            return ipLocation.getLocation();
        }

        String location = IPAddressUtil.getLocation(ip);
        ipLocationRepository.createOrUpdate(new IpLocation(ip, location));
        return location;
    }
}
