package com.icezhg.sunflower.service;

import com.icezhg.sunflower.domain.Openid;
import com.icezhg.sunflower.pojo.ChangeStatus;
import com.icezhg.sunflower.pojo.OpenidInfo;
import com.icezhg.sunflower.pojo.query.Query;

import java.util.List;

/**
 * Created by zhongjibing on 2023/07/13.
 */
public interface OpenidService {
    Openid findByOpenid(String openid);

    void save(Openid openid);

    Openid findById(Long id);

    int count(Query query);

    List<Openid> find(Query query);

    int changeStatus(ChangeStatus change);

    OpenidInfo update(OpenidInfo info);

    void updateLastLoginTime(String openid);

    void updateUid(Long id, String uid);
}
