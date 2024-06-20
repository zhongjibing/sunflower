package com.icezhg.sunflower.service;

import java.util.Map;

/**
 * Created by zhongjibing on 2023/06/20.
 */
public interface LoginRecordService {

    void saveLoginInfo(String userId, String username, Map<String, String> attributes);

    void saveLoginInfo(Long userId, String username, String status, String msg, Map<String, String> attributes);
}
