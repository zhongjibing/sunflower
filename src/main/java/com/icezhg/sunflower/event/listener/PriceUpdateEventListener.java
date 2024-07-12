package com.icezhg.sunflower.event.listener;


import com.icezhg.sunflower.event.PriceUpdateEvent;
import com.icezhg.sunflower.service.PricePlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Created by zhongjibing on 2022/09/10.
 */
@Component
public class PriceUpdateEventListener implements ApplicationListener<PriceUpdateEvent> {
    private static final Logger log = LoggerFactory.getLogger(PriceUpdateEventListener.class);

    private final PricePlanService pricePlanService;

    public PriceUpdateEventListener(PricePlanService pricePlanService) {
        this.pricePlanService = pricePlanService;
    }

    @Async
    @Override
    public void onApplicationEvent(PriceUpdateEvent event) {
        log.info("handle price update event: {}", event.getPriceRuleId());
        pricePlanService.generate(event.getPriceRuleId());
    }
}
