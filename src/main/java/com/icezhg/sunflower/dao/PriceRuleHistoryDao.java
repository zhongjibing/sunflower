package com.icezhg.sunflower.dao;

import com.icezhg.sunflower.domain.PriceRuleHistory;
import org.springframework.stereotype.Repository;

/**
 * Created by zhongjibing on 2023/07/08.
 */
@Repository
public interface PriceRuleHistoryDao {

    int insert(PriceRuleHistory record);

}
