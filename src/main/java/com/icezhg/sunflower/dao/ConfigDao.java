package com.icezhg.sunflower.dao;

import com.icezhg.sunflower.domain.Config;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2021/01/11
 */
@Repository
public interface ConfigDao {

    int insert(Config record);

    int update(Config record);

    int deleteByIds(List<Integer> ids);

    Config findById(Integer id);

    Config findByKey(String key);

    int count(Map<String, Object> query);

    List<Config> find(Map<String, Object> query);
}
