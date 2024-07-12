package com.icezhg.sunflower.event.listener;


import com.icezhg.sunflower.event.InventoryUpdateEvent;
import com.icezhg.sunflower.service.InventoryPlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by zhongjibing on 2022/09/10.
 */
@Component
public class InventoryUpdateEventListener implements ApplicationListener<InventoryUpdateEvent> {
    private static final Logger log = LoggerFactory.getLogger(InventoryUpdateEventListener.class);

    private final InventoryPlanService inventoryPlanService;

    public InventoryUpdateEventListener(InventoryPlanService inventoryPlanService) {
        this.inventoryPlanService = inventoryPlanService;
    }

    @Async
    @Override
    public void onApplicationEvent(InventoryUpdateEvent event) {
        log.info("handle inventory update event: {}", event.getInventoryId());
        this.inventoryPlanService.generate(event.getInventoryId());
    }
}
