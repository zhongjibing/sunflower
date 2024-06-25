package com.icezhg.sunflower.dao;

import com.icezhg.sunflower.domain.Session;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2023/06/25.
 */
@Repository
public interface SessionDao {

    int insert(Session session);

    int updateLastAccessedTime(@Param("sessionId") String sessionId, @Param("lastAccessedTime") Date lastAccessedTime);

    int deleteById(String id);
    int deleteBySessionId(String id);

    int count(Map<String, Object> query);

    List<Session> find(Map<String, Object> query);
}
