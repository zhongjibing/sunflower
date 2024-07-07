package com.icezhg.sunflower.controller.resource;

import com.icezhg.sunflower.common.Constant;
import com.icezhg.sunflower.domain.Resource;
import com.icezhg.sunflower.enums.ResourceType;
import com.icezhg.sunflower.pojo.ResourceInfo;

/**
 * Created by zhongjibing on 2023/07/06.
 */
public abstract class AbstractResourceController {

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
}
