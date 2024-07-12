package com.icezhg.sunflower.service.impl;

import com.icezhg.commons.exception.InvalidParameterException;
import com.icezhg.sunflower.dao.PriceRuleDao;
import com.icezhg.sunflower.domain.PriceRule;
import com.icezhg.sunflower.event.PriceUpdateEvent;
import com.icezhg.sunflower.pojo.PriceRuleDetail;
import com.icezhg.sunflower.pojo.PriceRuleInfo;
import com.icezhg.sunflower.pojo.query.DeleteQuery;
import com.icezhg.sunflower.pojo.query.Query;
import com.icezhg.sunflower.service.PriceRuleHistoryService;
import com.icezhg.sunflower.service.PriceRuleService;
import com.icezhg.sunflower.util.ApplicationContextUtil;
import com.icezhg.sunflower.util.CommonUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by zhongjibing on 2023/07/08.
 */
@Service
public class PriceRuleServiceImpl implements PriceRuleService {

    private final PriceRuleDao priceRuleDao;

    private PriceRuleHistoryService priceRuleHistoryService;

    public PriceRuleServiceImpl(PriceRuleDao priceRuleDao) {
        this.priceRuleDao = priceRuleDao;
    }

    @Autowired
    public void setPriceRuleHistoryService(PriceRuleHistoryService priceRuleHistoryService) {
        this.priceRuleHistoryService = priceRuleHistoryService;
    }

    @Override
    public PriceRuleInfo insert(PriceRule priceRule) {
        priceRule.setId(null);
        checkUnique(priceRule);
        CommonUtils.completeBaseInfo(priceRule);
        this.priceRuleDao.insert(priceRule);
        ApplicationContextUtil.publishEvent(new PriceUpdateEvent(priceRule.getId()));
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
        PriceRule existing = findById(priceRule.getId());
        if (existing == null) {
            throw new InvalidParameterException("", "parameter error");
        }

        CommonUtils.completeBaseInfo(priceRule);
        this.priceRuleDao.update(priceRule);
        this.priceRuleHistoryService.save(existing);
        if (priceRule.getDetail() != null && !StringUtils.equals(priceRule.getDetail(), existing.getDetail())) {
            ApplicationContextUtil.publishEvent(new PriceUpdateEvent(priceRule.getId()));
        }
        return buildPriceRuleInfo(findById(priceRule.getId()));
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            this.priceRuleDao.delete(DeleteQuery.of(ids).toMap());
        }
    }

    @Override
    public int count(Query query) {
        return this.priceRuleDao.count(query.toMap());
    }

    @Override
    public List<PriceRule> findAll(Integer type) {
        return this.priceRuleDao.findAllOfType(type);
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
