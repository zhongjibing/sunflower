package com.icezhg.sunflower.controller.price;

import com.icezhg.commons.exception.InvalidAccessException;
import com.icezhg.sunflower.domain.PriceRule;
import com.icezhg.sunflower.enums.ResourceType;
import com.icezhg.sunflower.pojo.PriceRuleInfo;
import com.icezhg.sunflower.service.PricePlanService;
import com.icezhg.sunflower.service.PriceRuleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by zhongjibing on 2023/07/08.
 */
public abstract class AbstractPriceController {

    protected PriceRuleService priceRuleService;
    protected PricePlanService pricePlanService;

    @Autowired
    public void setPriceRuleService(PriceRuleService priceRuleService) {
        this.priceRuleService = priceRuleService;
    }

    @Autowired
    public void setPricePlanService(PricePlanService pricePlanService) {
        this.pricePlanService = pricePlanService;
    }

    abstract ResourceType resourceType();

    protected PriceRule buildPriceRule(PriceRuleInfo info) {
        PriceRule priceRule = new PriceRule();
        priceRule.setId(info.getId());
        priceRule.setName(info.getName());
        priceRule.setTagId(info.getTagId());
        priceRule.setResourceId(info.getResourceId());
        priceRule.setType(resourceType().getType());
        priceRule.setDetail(info.getDetail());
        priceRule.setRemark(info.getRemark());
        return priceRule;
    }

    protected void checkDataPermission(List<Long> ids) {
        boolean matched = this.priceRuleService.findByIds(ids)
                .stream().allMatch(item -> Integer.valueOf(resourceType().getType()).equals(item.getType()));
        if (!matched) {
            throw new InvalidAccessException("", "access denied");
        }
    }

}
