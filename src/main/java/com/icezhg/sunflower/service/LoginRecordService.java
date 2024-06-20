package com.icezhg.sunflower.service;

import com.icezhg.sunflower.domain.LoginRecord;
import com.icezhg.sunflower.pojo.query.Query;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2023/06/20.
 */
public interface LoginRecordService {

    void saveLoginInfo(String userId, String username, Map<String, String> attributes);

    void saveLoginInfo(Long userId, String username, String status, String msg, Map<String, String> attributes);

    int count(Query query);

    List<LoginRecord> find(Query query);
}
