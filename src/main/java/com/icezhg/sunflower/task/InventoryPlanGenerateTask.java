package com.icezhg.sunflower.task;

import com.icezhg.sunflower.service.InventoryPlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by zhongjibing on 2023/07/12.
 */
@Component
public class InventoryPlanGenerateTask {
    private static final Logger log = LoggerFactory.getLogger(InventoryPlanGenerateTask.class);

    private final InventoryPlanService inventoryPlanService;

    public InventoryPlanGenerateTask(InventoryPlanService inventoryPlanService) {
        this.inventoryPlanService = inventoryPlanService;
    }

    public void generate(Integer type) {
        log.info("generate inventory plan. type={}", type);
        this.inventoryPlanService.generateByType(type);
    }
}
