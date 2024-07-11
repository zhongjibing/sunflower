package com.icezhg.sunflower.service;

import com.icezhg.sunflower.domain.Resource;
import com.icezhg.sunflower.enums.ResourceType;
import com.icezhg.sunflower.pojo.ChangeStatus;
import com.icezhg.sunflower.pojo.ResourceInfo;
import com.icezhg.sunflower.pojo.query.Query;

import java.util.List;

/**
 * Created by zhongjibing on 2023/07/06.
 */
public interface ResourceService {

    ResourceInfo insert(ResourceInfo info, ResourceType resourceType);

    ResourceInfo update(ResourceInfo info, ResourceType resourceType);

    Resource findById(Long id);

    int count(Query query);

    List<Resource> find(Query query);

    List<ResourceInfo> listAll(ResourceType resourceType);

    List<Resource> findByIds(List<Long> ids);

    void deleteByIds(List<Long> ids);

    void restoreByIds(List<Long> ids);

    int changeStatus(ChangeStatus change);
}
