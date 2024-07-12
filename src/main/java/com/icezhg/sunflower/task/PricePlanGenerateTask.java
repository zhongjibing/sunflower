package com.icezhg.sunflower.task;

import com.icezhg.sunflower.service.PricePlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Created by zhongjibing on 2023/07/12.
 */
@Component
public class PricePlanGenerateTask {
    private static final Logger log = LoggerFactory.getLogger(PricePlanGenerateTask.class);

    private final PricePlanService pricePlanService;

    public PricePlanGenerateTask(PricePlanService pricePlanService) {
        this.pricePlanService = pricePlanService;
    }

    public void generate(Integer type) {
        log.info("generate price plan. type={}", type);
        this.pricePlanService.generateByType(type);
    }
}
