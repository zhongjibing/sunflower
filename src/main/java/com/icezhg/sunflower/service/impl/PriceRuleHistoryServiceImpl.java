package com.icezhg.sunflower.service.impl;

import com.icezhg.sunflower.dao.PriceRuleHistoryDao;
import com.icezhg.sunflower.domain.PriceRule;
import com.icezhg.sunflower.domain.PriceRuleHistory;
import com.icezhg.sunflower.service.PriceRuleHistoryService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by zhongjibing on 2023/07/12.
 */
@Service
public class PriceRuleHistoryServiceImpl implements PriceRuleHistoryService {

    private final PriceRuleHistoryDao priceRuleHistoryDao;

    public PriceRuleHistoryServiceImpl(PriceRuleHistoryDao priceRuleHistoryDao) {
        this.priceRuleHistoryDao = priceRuleHistoryDao;
    }

    @Override
    public void save(PriceRule priceRule) {
        this.priceRuleHistoryDao.insert(buildPriceRuleHistory(priceRule));
    }

    private PriceRuleHistory buildPriceRuleHistory(PriceRule priceRule) {
        PriceRuleHistory history = new PriceRuleHistory();
        history.setRuleId(priceRule.getId());
        history.setTagId(priceRule.getTagId());
        history.setResourceId(priceRule.getResourceId());
        history.setType(priceRule.getType());
        history.setDetail(priceRule.getDetail());
        history.setDeleted(priceRule.getDeleted());
        history.setRemark(priceRule.getRemark());
        history.setCreateBy(priceRule.getCreateBy());
        history.setCreateTime(priceRule.getCreateTime());
        history.setUpdateBy(priceRule.getUpdateBy());
        history.setUpdateTime(priceRule.getUpdateTime());
        history.setOperateTime(new Date());
        return history;
    }
}
