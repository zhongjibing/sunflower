package com.icezhg.sunflower.service.impl;

import com.icezhg.sunflower.common.Constant;
import com.icezhg.sunflower.dao.LoginRecordDao;
import com.icezhg.sunflower.domain.LoginRecord;
import com.icezhg.sunflower.service.LoginRecordService;
import eu.bitwalker.useragentutils.UserAgent;
import jakarta.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by zhongjibing on 2023/06/20.
 */
@Service
public class LoginRecordServiceImpl implements LoginRecordService {

    private static final Logger log = LoggerFactory.getLogger(LoginRecordService.class);

    private LoginRecordDao loginRecordDao;

    @Autowired
    public void setLoginRecordDao(LoginRecordDao loginRecordDao) {
        this.loginRecordDao = loginRecordDao;
    }

    @Override
    @Transactional
    public void saveLoginInfo(String userId, String username, Map<String, String> attributes) {
        saveLoginInfo(Long.parseLong(userId), username, Constant.LOGIN_SUCCESS, "login success", attributes);
    }

    @Override
    @Transactional
    public void saveLoginInfo(Long userId, String username, String status, String msg, Map<String, String> attributes) {
        log.info("save login info: userId={}, username={}, status={}, msg={}, attributes={}", userId, username, status, msg, attributes);

        LoginRecord loginRecord = new LoginRecord();
        loginRecord.setUserId(userId != null ? userId : -1L);
        loginRecord.setUsername(username);
        loginRecord.setLoginTime(new Date());
        loginRecord.setStatus(status);

        if (StringUtils.isNotBlank(msg)) {
            loginRecord.setMsg(msg);
        } else {
            if (Constant.LOGIN_SUCCESS.equals(status)) {
                loginRecord.setMsg("login success");
            } else {
                loginRecord.setMsg("login failure");
            }
        }

        String ip = attributes.get(Constant.ATTRIBUTE_IP);
        if (ip != null) {
            loginRecord.setLoginIp(ip);
        }

        String ipLocation = attributes.get(Constant.ATTRIBUTE_IP_LOCATION);
        if (ipLocation != null) {
            loginRecord.setLoginLocation(ipLocation);
        }

        String agent = attributes.get(Constant.ATTRIBUTE_AGENT);
        if (agent != null) {
            loginRecord.setAgent(StringUtils.substring(agent, 0, 255));

            UserAgent userAgent = UserAgent.parseUserAgentString(agent);
            loginRecord.setOs(userAgent.getOperatingSystem().getName());
            loginRecord.setBrowser(userAgent.getBrowser().getName());
        }

        loginRecordDao.save(loginRecord);
    }
}
