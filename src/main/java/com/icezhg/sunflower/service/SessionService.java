package com.icezhg.sunflower.service;

import com.icezhg.sunflower.domain.Session;
import com.icezhg.sunflower.pojo.query.Query;

import java.util.Date;
import java.util.List;

/**
 * Created by zhongjibing on 2023/06/25.
 */
public interface SessionService {

    void save(Session session);

    void updateLastAccessedTime(String newSessionId, Date lastAccessedTime);

    void delete(String id);

    void deleteByOldSessionId(String sessionId);

    int count(Query query);

    List<Session> find(Query query);

    void forceLogout(String id);

    void cleanExpired();
}
