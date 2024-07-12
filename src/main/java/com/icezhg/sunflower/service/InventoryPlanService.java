package com.icezhg.sunflower.service;

import java.time.LocalDate;

/**
 * Created by zhongjibing on 2023/07/12.
 */
public interface InventoryPlanService {
    void generate(Long inventoryId);

    void generate(Long inventoryId, LocalDate date);

    void generateByType(Integer type);
}
