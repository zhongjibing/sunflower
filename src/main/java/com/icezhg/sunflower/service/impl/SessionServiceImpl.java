package com.icezhg.sunflower.service.impl;

import com.icezhg.commons.util.IdGenerator;
import com.icezhg.sunflower.common.CacheKey;
import com.icezhg.sunflower.dao.SessionDao;
import com.icezhg.sunflower.domain.Session;
import com.icezhg.sunflower.pojo.query.Query;
import com.icezhg.sunflower.service.SessionService;
import com.icezhg.sunflower.util.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2023/06/25.
 */
@Service
public class SessionServiceImpl implements SessionService {

    private final SessionDao sessionDao;

    private RedisTemplate<Object, Object> redisTemplate;

    public SessionServiceImpl(SessionDao sessionDao) {
        this.sessionDao = sessionDao;
    }

    @Autowired
    public void setRedisTemplate(RedisTemplate<Object, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
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
    public void updateLastAccessedTime(String newSessionId, Date lastAccessedTime) {
        if (newSessionId != null) {
            Map<String, Object> params = new HashMap<>();
            params.put("newSessionId", newSessionId);
            params.put("lastAccessedTime", lastAccessedTime != null ? lastAccessedTime : new Date());
            params.put("updateTime", new Date());
            this.sessionDao.updateLastAccessedTime(params);
        }
    }

    @Override
    public void delete(String id) {
        this.sessionDao.deleteById(id);
    }

    @Override
    public void deleteByOldSessionId(String oldSessionId) {
        this.sessionDao.deleteByOldSessionId(oldSessionId);
    }

    @Override
    public int count(Query query) {
        return this.sessionDao.count(query.toMap());
    }

    @Override
    public List<Session> find(Query query) {
        return this.sessionDao.find(query.toMap());
    }

    @Override
    public void forceLogout(String id) {
        Session session = this.sessionDao.findById(id);
        if (session == null) {
            return;
        }
        if (StringUtils.equals(session.getNewSessionId(), SecurityUtil.getSessionId())) {
            return;
        }

        this.redisTemplate.delete(CacheKey.SESSION_KEY_PREFIX + session.getNewSessionId());
        this.sessionDao.deleteById(id);
    }
}
