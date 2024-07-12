package com.icezhg.sunflower.dao;


import com.icezhg.sunflower.domain.InventoryPlan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhongjibing on 2023/07/12.
 */
@Repository
public interface InventoryPlanDao {

    int insert(InventoryPlan record);

    int batchInsert(List<InventoryPlan> records);

    InventoryPlan findById(Long id);

}
