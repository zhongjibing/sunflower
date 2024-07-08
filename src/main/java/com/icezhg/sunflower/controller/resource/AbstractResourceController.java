package com.icezhg.sunflower.controller.resource;

import com.icezhg.commons.exception.InvalidAccessException;
import com.icezhg.sunflower.common.Constant;
import com.icezhg.sunflower.domain.Resource;
import com.icezhg.sunflower.enums.ResourceType;
import com.icezhg.sunflower.pojo.ResourceInfo;
import com.icezhg.sunflower.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zhongjibing on 2023/07/06.
 */
public abstract class AbstractResourceController {

    protected ResourceService resourceService;

    @Autowired
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    abstract ResourceType resourceType();

    protected Resource buildResource(ResourceInfo info) {
        Resource resource = new Resource();
        resource.setId(info.getId());
        resource.setName(info.getName());
        resource.setDescription(info.getDescription());
        resource.setType(resourceType().getType());
        resource.setNumber(info.getNumber());
        resource.setStatus(Constant.NORMAL);
        resource.setRemark(info.getRemark());
        return resource;
    }

    protected void checkDataPermission(List<Long> resourceIds) {
        boolean matched = this.resourceService.findByIds(resourceIds)
                .stream().allMatch(item -> Integer.valueOf(resourceType().getType()).equals(item.getType()));
        if (!matched) {
            throw new InvalidAccessException("", "access denied");
        }
    }

}
