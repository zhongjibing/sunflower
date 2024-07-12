package com.icezhg.sunflower.dao;


import com.icezhg.sunflower.domain.PricePlan;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhongjibing on 2023/07/08.
 */
@Repository
public interface PricePlanDao {

    int insert(PricePlan record);

    int batchInsert(List<PricePlan> records);

    PricePlan findById(Long id);
}
