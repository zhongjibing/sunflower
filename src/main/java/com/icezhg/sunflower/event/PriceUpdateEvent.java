package com.icezhg.sunflower.event;

import org.springframework.context.ApplicationEvent;

/**
 * Created by zhongjibing on 2023/07/12.
 */
public class PriceUpdateEvent extends ApplicationEvent {
    public PriceUpdateEvent(Long priceRuleId) {
        super(priceRuleId);
    }

    public Long getPriceRuleId() {
        return (Long) getSource();
    }
}
