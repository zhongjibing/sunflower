package com.icezhg.sunflower.event.listener;


import com.icezhg.sunflower.event.InventoryUpdateEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by zhongjibing on 2022/09/10.
 */
@Component
public class InventoryUpdateEventListener implements ApplicationListener<InventoryUpdateEvent> {


    @Async
    @Override
    public void onApplicationEvent(InventoryUpdateEvent event) {

    }
}
