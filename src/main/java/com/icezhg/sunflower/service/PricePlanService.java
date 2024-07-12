package com.icezhg.sunflower.service;

import java.time.LocalDate;

/**
 * Created by zhongjibing on 2023/07/08.
 */
public interface PricePlanService {

    void generate(Long priceRuleId);

    void generate(Long priceRuleId, LocalDate date);

    void generate(Integer type);
}
