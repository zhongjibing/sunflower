package com.icezhg.sunflower.service.impl;

import com.icezhg.sunflower.dao.PriceRuleDao;
import com.icezhg.sunflower.domain.PriceRule;
import com.icezhg.sunflower.pojo.PriceRuleDetail;
import com.icezhg.sunflower.pojo.PriceRuleInfo;
import com.icezhg.sunflower.pojo.query.DeleteQuery;
import com.icezhg.sunflower.pojo.query.Query;
import com.icezhg.sunflower.service.PriceRuleService;
import com.icezhg.sunflower.util.CommonUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by zhongjibing on 2023/07/08.
 */
@Service
public class PriceRuleServiceImpl implements PriceRuleService {

    private final PriceRuleDao priceRuleDao;

    public PriceRuleServiceImpl(PriceRuleDao priceRuleDao) {
        this.priceRuleDao = priceRuleDao;
    }

    @Override
    public PriceRuleInfo insert(PriceRule priceRule) {
        priceRule.setId(null);
        CommonUtils.completeBaseInfo(priceRule);
        this.priceRuleDao.insert(priceRule);
        return buildPriceRuleInfo(this.priceRuleDao.findById(priceRule.getId()));
    }

    @Override
    public PriceRuleInfo update(PriceRule priceRule) {
        CommonUtils.completeBaseInfo(priceRule);
        this.priceRuleDao.update(priceRule);
        return buildPriceRuleInfo(this.priceRuleDao.findById(priceRule.getId()));
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        this.priceRuleDao.delete(DeleteQuery.of(ids).toMap());
    }

    @Override
    public int count(Query query) {
        return this.priceRuleDao.count(query.toMap());
    }

    @Override
    public List<PriceRuleDetail> find(Query query) {
        return this.priceRuleDao.findPriceRuleDetails(query.toMap());
    }

    @Override
    public PriceRuleDetail findById(Long id) {
        return this.priceRuleDao.findPriceRuleDetailById(id);
    }

    @Override
    public List<PriceRule> findByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return this.priceRuleDao.findByIds(ids);
    }

    private PriceRuleInfo buildPriceRuleInfo(PriceRule priceRule) {
        PriceRuleInfo priceRuleInfo = new PriceRuleInfo();
        if (priceRule != null) {
            priceRuleInfo.setId(priceRule.getId());
            priceRuleInfo.setName(priceRule.getName());
            priceRuleInfo.setTagId(priceRule.getTagId());
            priceRuleInfo.setResourceId(priceRule.getResourceId());
            priceRuleInfo.setDetail(priceRule.getDetail());
            priceRuleInfo.setRemark(priceRule.getRemark());
        }
        return priceRuleInfo;
    }
}
