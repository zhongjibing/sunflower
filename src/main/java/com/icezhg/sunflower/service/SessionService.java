package com.icezhg.sunflower.service;

import com.icezhg.sunflower.domain.Session;
import com.icezhg.sunflower.pojo.query.Query;
import com.icezhg.sunflower.security.UserDetail;

import java.util.Date;
import java.util.List;

/**
 * Created by zhongjibing on 2023/06/25.
 */
public interface SessionService {

    void save(Session session);

    void save(UserDetail userDetail);

    void updateLastAccessedTime(String sessionId, Date lastAccessedTime);

    void delete(String id);

    void deleteBySessionId(String sessionId);

    int count(Query query);

    List<Session> find(Query query);
}
