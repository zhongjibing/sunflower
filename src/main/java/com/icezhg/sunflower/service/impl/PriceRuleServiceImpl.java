package com.icezhg.sunflower.service.impl;

import com.icezhg.commons.exception.InvalidParameterException;
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
        checkUnique(priceRule);
        CommonUtils.completeBaseInfo(priceRule);
        this.priceRuleDao.insert(priceRule);
        return buildPriceRuleInfo(findById(priceRule.getId()));
    }

    private void checkUnique(PriceRule priceRule) {
        PriceRule existing = priceRuleDao.findByResourceIdAndTagId(priceRule.getResourceId(), priceRule.getTagId());
        if (existing != null && (priceRule.getId() == null || !priceRule.getId().equals(existing.getId()))) {
            throw new InvalidParameterException("", "a record already exists with same resource and tag");
        }
    }

    @Override
    public PriceRuleInfo update(PriceRule priceRule) {
        checkUnique(priceRule);
        CommonUtils.completeBaseInfo(priceRule);
        this.priceRuleDao.update(priceRule);
        return buildPriceRuleInfo(findById(priceRule.getId()));
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
    public List<PriceRuleDetail> findDetails(Query query) {
        return this.priceRuleDao.findPriceRuleDetails(query.toMap());
    }

    @Override
    public PriceRule findById(Long id) {
        return this.priceRuleDao.findById(id);
    }

    @Override
    public PriceRuleDetail findDetailById(Long id) {
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
