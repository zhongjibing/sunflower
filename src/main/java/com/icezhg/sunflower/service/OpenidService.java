package com.icezhg.sunflower.service;

import com.icezhg.sunflower.domain.Openid;

/**
 * Created by zhongjibing on 2023/07/13.
 */
public interface OpenidService {
    Openid findByOpenid(String openid);

    void save(Openid openid);
}
