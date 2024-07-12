package com.icezhg.sunflower.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by zhongjibing on 2023/07/12.
 */
public class InventoryUpdateEvent extends ApplicationEvent {
    public InventoryUpdateEvent(Long inventoryId) {
        super(inventoryId);
    }

    public Long getInventoryId() {
        return (Long) getSource();
    }
}
