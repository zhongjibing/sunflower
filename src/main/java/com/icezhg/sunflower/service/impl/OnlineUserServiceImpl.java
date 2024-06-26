package com.icezhg.sunflower.service.impl;


import com.icezhg.sunflower.common.Constant;
import com.icezhg.sunflower.domain.Session;
import com.icezhg.sunflower.pojo.OnlineUser;
import com.icezhg.sunflower.pojo.PageResult;
import com.icezhg.sunflower.pojo.query.OnlineUserQuery;
import com.icezhg.sunflower.service.OnlineUserService;
import com.icezhg.sunflower.service.SessionService;
import com.icezhg.sunflower.util.SecurityUtil;
import eu.bitwalker.useragentutils.UserAgent;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
public class OnlineUserServiceImpl implements OnlineUserService {
    private static final Logger log = LoggerFactory.getLogger(OnlineUserServiceImpl.class);

    private SessionService sessionService;

    @Autowired
    public void setSessionService(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public PageResult listOnlineUsers(OnlineUserQuery query) {
        int total = this.sessionService.count(query);
        List<Session> sessions = this.sessionService.find(query);
        List<OnlineUser> onlineUsers = sessions.stream().map(this::buildOnlineUser).toList();
        return new PageResult(total, onlineUsers);
    }

    private OnlineUser buildOnlineUser(Session session) {
        OnlineUser user = new OnlineUser();
        user.setId(session.getId());
        user.setUserId(session.getUserId());
        user.setSessionId(session.getNewSessionId());
        user.setUsername(session.getUsername());
        user.setName(session.getName());
        user.setNickname(session.getNickname());
        user.setLoginIp(session.getLoginIp());
        user.setLoginLocation(session.getLoginLocation());
        user.setPicture(session.getAvatar());
        user.setLastAccessedTime(formatDateTime(session.getLastAccessedTime()));
        user.setLoginTime(formatDateTime(session.getLoginTime()));
        user.setUserAgent(session.getAgent());
        user.setCurrentSession(StringUtils.equals(session.getNewSessionId(), SecurityUtil.getSessionId()));

        UserAgent userAgent = UserAgent.parseUserAgentString(session.getAgent());
        user.setBrowser(userAgent.getBrowser().getName());
        user.setOs(userAgent.getOperatingSystem().getName());
        return user;
    }

    @Override
    public void forceLogout(String id) {
        sessionService.forceLogout(id);
    }


    private String formatDateTime(Date date) {
        if (date == null) {
            return "";
        }

        return DateFormatUtils.format(date, Constant.DEFAULT_DATETIME_FORMAT_PATTERN, Constant.DEFAULT_LOCALE);
    }

    private long parseTime(String datetime) {
        try {
            return DateUtils.parseDate(datetime, Constant.DEFAULT_LOCALE, Constant.DEFAULT_DATETIME_FORMAT_PATTERN).getTime();
        } catch (ParseException ignored) {
        }
        return 0L;
    }
}
