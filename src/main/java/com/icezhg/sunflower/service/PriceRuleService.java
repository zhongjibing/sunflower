package com.icezhg.sunflower.service;

import com.icezhg.sunflower.domain.PriceRule;
import com.icezhg.sunflower.pojo.PriceRuleDetail;
import com.icezhg.sunflower.pojo.query.Query;

import java.util.List;

/**
 * Created by zhongjibing on 2023/07/08.
 */
public interface PriceRuleService {
    Object insert(PriceRule priceRule);

    Object update(PriceRule priceRule);

    void deleteByIds(List<Long> ids);

    int count(Query query);

    List<PriceRule> findAll(Integer type);

    List<PriceRuleDetail> findDetails(Query query);

    PriceRule findById(Long id);

    PriceRuleDetail findDetailById(Long id);

    List<PriceRule> findByIds(List<Long> ids);
}
