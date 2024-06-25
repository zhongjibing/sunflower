package com.icezhg.sunflower.service.impl;

import com.icezhg.commons.util.IdGenerator;
import com.icezhg.sunflower.common.Constant;
import com.icezhg.sunflower.dao.SessionDao;
import com.icezhg.sunflower.domain.Session;
import com.icezhg.sunflower.pojo.query.Query;
import com.icezhg.sunflower.security.UserDetail;
import com.icezhg.sunflower.service.SessionService;
import com.icezhg.sunflower.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zhongjibing on 2023/06/25.
 */
@Service
public class SessionServiceImpl implements SessionService {

    private final SessionDao sessionDao;

    public SessionServiceImpl(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    @Override
    public void save(Session session) {
        if (session.getId() == null) {
            session.setId(IdGenerator.nextId());
        }
        if (session.getCreateTime() == null) {
            session.setCreateTime(new Date());
        }
        if (session.getUpdateTime() == null) {
            session.setUpdateTime(new Date());
        }
        this.sessionDao.insert(session);
    }

    @Override
    public void save(UserDetail userDetail) {
        Session session = new Session();
        session.setSessionId(SecurityUtil.getSessionId());
        session.setUserId(userDetail.getId());
        session.setUsername(userDetail.getUsername());
        session.setName(userDetail.getName());
        session.setNickname(userDetail.getNickname());
        session.setAvatar(userDetail.getAvatar());
        session.setLoginIp(userDetail.getAttributes().get(Constant.ATTRIBUTE_IP));
        session.setLoginLocation(userDetail.getAttributes().get(Constant.ATTRIBUTE_IP_LOCATION));
        session.setAgent(userDetail.getAttributes().get(Constant.ATTRIBUTE_AGENT));
        session.setLoginTime(new Date());
        session.setLastAccessedTime(new Date());
        this.save(session);
    }

    @Override
    public void updateLastAccessedTime(String sessionId, Date lastAccessedTime) {
        if (sessionId != null) {
            Date time = lastAccessedTime != null ? lastAccessedTime : new Date();
            this.sessionDao.updateLastAccessedTime(sessionId, time);
        }
    }

    @Override
    public void delete(String id) {
        this.sessionDao.deleteById(id);
    }

    @Override
    public void deleteBySessionId(String sessionId) {
        this.sessionDao.deleteBySessionId(sessionId);
    }

    @Override
    public int count(Query query) {
        return this.sessionDao.count(query.toMap());
    }

    @Override
    public List<Session> find(Query query) {
        return this.sessionDao.find(query.toMap());
    }
}
