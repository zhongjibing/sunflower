package com.icezhg.sunflower.dao;

import com.icezhg.sunflower.domain.Resource;
import com.icezhg.sunflower.pojo.ResourceInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2023/07/06.
 */
@Repository
public interface ResourceDao {

    int delete(Map<String, Object> params);

    int insert(Resource record);

    Resource findById(Long id);

    List<Resource> findByIds(List<Long> ids);

    int update(Resource record);

    int count(Map<String, Object> params);

    List<Resource> find(Map<String, Object> params);

    List<ResourceInfo> findByType(Integer type);

    int restoreByIds(Map<String, Object> params);
}
