package com.icezhg.sunflower.service;

import com.icezhg.sunflower.domain.LoginRecord;
import com.icezhg.sunflower.pojo.query.Query;
import com.icezhg.sunflower.security.UserDetail;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2023/06/20.
 */
public interface LoginRecordService {

    void saveLoginInfo(UserDetail userDetail);

    void saveLoginInfo(Long userId, String username, String status, String msg, Map<String, String> attributes, int loginMethod);

    int count(Query query);

    List<LoginRecord> find(Query query);
}
