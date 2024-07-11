package com.icezhg.sunflower.service.impl;

import com.icezhg.commons.exception.InvalidDataStateException;
import com.icezhg.sunflower.common.Constant;
import com.icezhg.sunflower.common.SysConfig;
import com.icezhg.sunflower.dao.ResourceDao;
import com.icezhg.sunflower.domain.Resource;
import com.icezhg.sunflower.enums.ResourceType;
import com.icezhg.sunflower.pojo.ChangeStatus;
import com.icezhg.sunflower.pojo.ResourceInfo;
import com.icezhg.sunflower.pojo.query.DeleteQuery;
import com.icezhg.sunflower.pojo.query.Query;
import com.icezhg.sunflower.pojo.query.ResourceQuery;
import com.icezhg.sunflower.service.ConfigService;
import com.icezhg.sunflower.service.ResourceService;
import com.icezhg.sunflower.util.CommonUtils;
import com.icezhg.sunflower.util.SecurityUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by zhongjibing on 2023/07/06.
 */
@Service
public class ResourceServiceImpl implements ResourceService {

    private final ResourceDao resourceDao;

    private ConfigService configService;

    public ResourceServiceImpl(ResourceDao resourceDao) {
        this.resourceDao = resourceDao;
    }

    @Autowired
    public void setConfigService(ConfigService configService) {
        this.configService = configService;
    }

    @Override
    public ResourceInfo insert(ResourceInfo info, ResourceType resourceType) {
        checkCreatable(resourceType);

        Resource resource = buildResource(info, resourceType);
        resource.setId(null);
        CommonUtils.completeBaseInfo(resource);
        this.resourceDao.insert(resource);
        return buildResourceInfo(findById(resource.getId()));
    }

    @Override
    public ResourceInfo update(ResourceInfo info, ResourceType resourceType) {
        Resource resource = buildResource(info, resourceType);
        CommonUtils.completeBaseInfo(resource);
        this.resourceDao.update(resource);
        return buildResourceInfo(findById(resource.getId()));
    }

    @Override
    public Resource findById(Long id) {
        return this.resourceDao.findById(id);
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
    public List<Resource> listAll(ResourceType resourceType) {
        ResourceQuery query = new ResourceQuery();
        query.setType(resourceType.getType());
        return resourceDao.findAll(query.toMap());
    }

    @Override
    public List<Resource> findByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }

        return this.resourceDao.findByIds(ids);
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            this.resourceDao.delete(DeleteQuery.of(ids).toMap());
        }
    }

    @Override
    public void restoreByIds(List<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            this.resourceDao.restoreByIds(DeleteQuery.of(ids).toMap());
        }
    }

    @Override
    public int changeStatus(ChangeStatus change) {
        Resource resource = new Resource();
        resource.setId(change.getId());
        resource.setStatus(change.getStatus());
        resource.setUpdateBy(SecurityUtil.currentUserName());
        resource.setUpdateTime(new Date());
        return this.resourceDao.update(resource);
    }

    private ResourceInfo buildResourceInfo(Resource resource) {
        ResourceInfo info = new ResourceInfo();
        if (resource != null) {
            info.setId(resource.getId());
            info.setName(resource.getName());
            info.setDescription(resource.getDescription());
            info.setDetail(resource.getDetail());
            info.setRemark(resource.getRemark());
        }
        return info;
    }

    protected Resource buildResource(ResourceInfo info, ResourceType resourceType) {
        Resource resource = new Resource();
        resource.setId(info.getId());
        resource.setName(info.getName());
        resource.setDescription(info.getDescription());
        resource.setType(resourceType.getType());
        resource.setDetail(info.getDetail());
        resource.setStatus(Constant.NORMAL);
        resource.setRemark(info.getRemark());
        return resource;
    }

    private int maxCreateAmount() {
        String amountLimit = configService.findConfig(SysConfig.RESOURCE_AMOUNT_LIMIT);
        return NumberUtils.toInt(amountLimit, 1);
    }

    private void checkCreatable(ResourceType resourceType) {
        if (SecurityUtil.isRootUser()) {
            return;
        }

        ResourceQuery query = new ResourceQuery();
        query.setType(resourceType.getType());
        int total = resourceDao.count(query.toMap());
        if (total >= maxCreateAmount()) {
            throw new InvalidDataStateException("", "the number of resources reaches the upper limit");
        }
    }

}
