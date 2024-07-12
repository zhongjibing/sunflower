package com.icezhg.sunflower.event;

import com.icezhg.sunflower.domain.PriceRule;
import org.springframework.context.ApplicationEvent;

/**
 * Created by zhongjibing on 2023/07/12.
 */
public class PriceUpdateEvent extends ApplicationEvent {
    public PriceUpdateEvent(PriceRule priceRule) {
        super(priceRule);
    }

    public Long getPriceRuleId() {
        return ((PriceRule) getSource()).getId();
    }
}
