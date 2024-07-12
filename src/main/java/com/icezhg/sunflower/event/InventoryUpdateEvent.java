package com.icezhg.sunflower.event;

import com.icezhg.sunflower.domain.Inventory;
import org.springframework.context.ApplicationEvent;

/**
 * Created by zhongjibing on 2023/07/12.
 */
public class InventoryUpdateEvent extends ApplicationEvent {
    public InventoryUpdateEvent(Inventory inventory) {
        super(inventory);
    }

    public Long getInventoryId() {
        return ((Inventory) getSource()).getId();
    }
}
