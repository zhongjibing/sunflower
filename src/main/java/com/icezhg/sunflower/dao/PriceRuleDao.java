package com.icezhg.sunflower.dao;


import com.icezhg.sunflower.domain.PriceRule;
import com.icezhg.sunflower.pojo.PriceRuleDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2023/07/08.
 */
@Repository
public interface PriceRuleDao {

    int delete(Map<String, Object> params);

    int insert(PriceRule record);

    PriceRule findById(Long id);

    List<PriceRule> findByIds(List<Long> ids);

    PriceRuleDetail findPriceRuleDetailById(Long id);

    int update(PriceRule record);

    int count(Map<String, Object> params);

    List<PriceRuleDetail> findPriceRuleDetails(Map<String, Object> params);

}
