package com.icezhg.sunflower.controller.inventory;

import com.icezhg.commons.exception.InvalidAccessException;
import com.icezhg.sunflower.enums.ResourceType;
import com.icezhg.sunflower.service.InventoryPlanService;
import com.icezhg.sunflower.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zhongjibing on 2023/07/12.
 */
public abstract class AbstractInventoryController {

    protected InventoryService inventoryService;
    protected InventoryPlanService inventoryPlanService;

    @Autowired
    public void setInventoryService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Autowired
    public void setInventoryPlanService(InventoryPlanService inventoryPlanService) {
        this.inventoryPlanService = inventoryPlanService;
    }

    abstract ResourceType resourceType();


    protected void checkDataPermission(List<Long> ids) {
        boolean matched = this.inventoryService.findByIds(ids)
                .stream().allMatch(item -> Integer.valueOf(resourceType().getType()).equals(item.getType()));
        if (!matched) {
            throw new InvalidAccessException("", "access denied");
        }
    }

}
