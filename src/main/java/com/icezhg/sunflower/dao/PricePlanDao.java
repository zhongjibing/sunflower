package com.icezhg.sunflower.dao;


import com.icezhg.sunflower.domain.PricePlan;
import org.springframework.stereotype.Repository;

/**
 * Created by zhongjibing on 2023/07/08.
 */
@Repository
public interface PricePlanDao {

    int insert(PricePlan record);

    PricePlan findById(Long id);

    int update(PricePlan record);

}
