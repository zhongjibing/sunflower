package com.icezhg.sunflower.service;

import com.icezhg.sunflower.domain.Resource;
import com.icezhg.sunflower.pojo.query.Query;

import java.util.List;

/**
 * Created by zhongjibing on 2023/07/06.
 */
public interface ResourceService {

    Object insert(Resource resource);

    Object update(Resource resource);

    Resource findById(String id);

    void delete(String id);

    int count(Query query);

    List<Resource> find(Query query);

    void deleteByIds(List<String> ids);
}
