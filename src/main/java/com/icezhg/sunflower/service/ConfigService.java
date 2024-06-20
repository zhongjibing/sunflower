package com.icezhg.sunflower.service;


import com.icezhg.sunflower.domain.Config;
import com.icezhg.sunflower.pojo.ConfigInfo;
import com.icezhg.sunflower.pojo.query.Query;

import java.util.List;

/**
 * Created by zhongjibing on 2022/09/13.
 */
public interface ConfigService {

    int count(Query query);

    List<Config> find(Query query);

    String findConfig(String key);

    boolean checkUnique(ConfigInfo configInfo);

    ConfigInfo save(ConfigInfo configInfo);

    ConfigInfo update(ConfigInfo configInfo);

    int deleteByIds(List<Integer> ids);

    ConfigInfo findById(Integer id);

    ConfigInfo findByKey(String key);
}
