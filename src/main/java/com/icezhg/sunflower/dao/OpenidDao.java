package com.icezhg.sunflower.dao;


import com.icezhg.sunflower.domain.Openid;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2023/07/13.
 */
@Repository
public interface OpenidDao {

    Openid findByOpenid(String openid);

    int insert(Openid record);

    int update(Openid record);

    Openid findById(Long id);

    int count(Map<String, Object> params);

    List<Openid> find(Map<String, Object> params);

}
