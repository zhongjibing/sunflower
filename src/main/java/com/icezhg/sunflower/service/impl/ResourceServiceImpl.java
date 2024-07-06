package com.icezhg.sunflower.service.impl;

import com.icezhg.commons.util.IdGenerator;
import com.icezhg.sunflower.dao.ResourceDao;
import com.icezhg.sunflower.domain.Resource;
import com.icezhg.sunflower.pojo.ResourceInfo;
import com.icezhg.sunflower.pojo.query.DeleteQuery;
import com.icezhg.sunflower.pojo.query.Query;
import com.icezhg.sunflower.service.ResourceService;
import com.icezhg.sunflower.util.CommonUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhongjibing on 2023/07/06.
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    private final ResourceDao resourceDao;

    public ResourceServiceImpl(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }

    @Override
    public ResourceInfo insert(Resource resource) {
        resource.setId(IdGenerator.nextId());
        CommonUtils.completeBaseInfo(resource);
        this.resourceDao.insert(resource);
        return buildResourceInfo(findById(resource.getId()));
    }

    @Override
    public ResourceInfo update(Resource resource) {
        CommonUtils.completeBaseInfo(resource);
        this.resourceDao.update(resource);
        return buildResourceInfo(findById(resource.getId()));
    }

    @Override
    public Resource findById(String id) {
        return this.resourceDao.findById(id);
    }

    @Override
    public void delete(String id) {
        this.resourceDao.delete(DeleteQuery.of(id).toMap());
    }

    @Override
    public int count(Query query) {
        return this.resourceDao.count(query.toMap());
    }

    @Override
    public List<Resource> find(Query query) {
        return this.resourceDao.find(query.toMap());
    }

    @Override
    public void deleteByIds(List<String> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            this.resourceDao.deleteByIds(DeleteQuery.of(ids).toMap());
        }
    }

    private ResourceInfo buildResourceInfo(Resource resource) {
        ResourceInfo info = new ResourceInfo();
        if (resource != null) {
            info.setId(resource.getId());
            info.setName(resource.getName());
            info.setDescription(resource.getDescription());
            info.setNumber(resource.getNumber());
            info.setRemark(resource.getRemark());
        }
        return info;
    }

}
