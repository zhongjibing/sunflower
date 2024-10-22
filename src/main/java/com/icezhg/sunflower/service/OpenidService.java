package com.icezhg.sunflower.service;

import com.icezhg.sunflower.domain.Openid;
import com.icezhg.sunflower.enums.WxRole;
import com.icezhg.sunflower.pojo.BizOpenid;
import com.icezhg.sunflower.pojo.ChangeStatus;
import com.icezhg.sunflower.pojo.OpenidInfo;
import com.icezhg.sunflower.pojo.OpenidUser;
import com.icezhg.sunflower.pojo.query.Query;

import java.util.List;

/**
 * Created by zhongjibing on 2023/07/13.
 */
public interface OpenidService {
    Openid findByOpenid(String openid);

    Openid create(String openid);

    void save(Openid openid);

    Openid findById(Long id);

    int count(Query query);

    List<Openid> find(Query query);

    int changeStatus(ChangeStatus change);

    OpenidInfo update(OpenidUser info);

    void update(BizOpenid info);

    void updateLastLoginTime(String openid);

    void updateUid(Long id, String uid);

    int changeRole(Long id, WxRole role);
}
