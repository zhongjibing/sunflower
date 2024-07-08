package com.icezhg.sunflower.dao;

import com.icezhg.sunflower.domain.Resource;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2023/07/06.
 */
@Repository
public interface ResourceDao {

    int delete(Map<String, Object> params);

    int deleteByIds(Map<String, Object> params);

    int insert(Resource record);

    Resource findById(Long id);

    int update(Resource record);

    int count(Map<String, Object> params);

    List<Resource> find(Map<String, Object> params);

    int restoreByIds(Map<String, Object> params);
}