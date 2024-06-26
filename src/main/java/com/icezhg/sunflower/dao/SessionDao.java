package com.icezhg.sunflower.dao;

import com.icezhg.sunflower.domain.Session;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2023/06/25.
 */
@Repository
public interface SessionDao {

    int insert(Session session);

    int updateLastAccessedTime(Map<String, Object> params);

    int deleteById(String id);

    int deleteByOldSessionId(String oldSessionId);

    int count(Map<String, Object> query);

    List<Session> find(Map<String, Object> query);
}
