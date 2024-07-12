package com.icezhg.sunflower.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.icezhg.sunflower.dao.PricePlanDao;
import com.icezhg.sunflower.domain.PricePlan;
import com.icezhg.sunflower.domain.PriceRule;
import com.icezhg.sunflower.pojo.PriceDetail;
import com.icezhg.sunflower.service.PricePlanService;
import com.icezhg.sunflower.service.PriceRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhongjibing on 2023/07/08.
 */
@Service
public class PricePlanServiceImpl implements PricePlanService {
    private static final Logger log = LoggerFactory.getLogger(PricePlanServiceImpl.class);

    private final PricePlanDao pricePlanDao;

    private PriceRuleService priceRuleService;

    public PricePlanServiceImpl(PricePlanDao pricePlanDao) {
        this.pricePlanDao = pricePlanDao;
    }

    @Autowired
    public void setPriceRuleService(PriceRuleService priceRuleService) {
        this.priceRuleService = priceRuleService;
    }

    @Override
    public void generate(Long priceRuleId) {
        PriceRule priceRule = this.priceRuleService.findById(priceRuleId);
        if (priceRule == null) {
            log.warn("price rule is not exist. id={}", priceRuleId);
            return;
        }

        List<PricePlan> pricePlans = new LinkedList<>();
        LocalDate date = LocalDate.now(ZoneId.systemDefault());
        LocalDate nextMonth = LocalDate.now().plusMonths(1);
        while (date.isBefore(nextMonth)) {
            pricePlans.add(generatePricePlan(priceRule, date));
            date = date.plusDays(1);
        }

        this.pricePlanDao.batchInsert(pricePlans);
    }

    @Override
    public void generate(Long priceRuleId, LocalDate date) {
        PriceRule priceRule = this.priceRuleService.findById(priceRuleId);
        if (priceRule == null) {
            log.warn("price rule is not exist. id={}", priceRuleId);
            return;
        }

        this.pricePlanDao.batchInsert(List.of(generatePricePlan(priceRule, date)));
    }

    @Override
    public void generateByType(Integer type) {
        List<PriceRule> priceRules = this.priceRuleService.findAll(type);
        if (priceRules.isEmpty()) {
            log.warn("price rules is empty. type={}", type);
            return;
        }
        List<PricePlan> pricePlans = new ArrayList<>(priceRules.size());
        LocalDate date = LocalDate.now(ZoneId.systemDefault()).plusMonths(1);
        for (PriceRule priceRule : priceRules) {
            pricePlans.add(generatePricePlan(priceRule, date));
        }
        this.pricePlanDao.batchInsert(pricePlans);
    }

    private PricePlan generatePricePlan(PriceRule priceRule, LocalDate date) {
        PricePlan pricePlan = new PricePlan();
        pricePlan.setRuleId(priceRule.getId());
        pricePlan.setDate(parseDate(date));
        pricePlan.setResourceId(priceRule.getResourceId());
        pricePlan.setType(priceRule.getType());
        pricePlan.setPrice(determinePrice(priceRule.getDetail(), date));
        pricePlan.setCreateTime(new Date());
        pricePlan.setUpdateTime(new Date());
        return pricePlan;
    }

    private long determinePrice(String detail, LocalDate date) {
        PriceDetail priceDetail = JSONObject.parseObject(detail, PriceDetail.class);
        if (priceDetail == null) {
            return 0L;
        }

        long result = priceDetail.getPrice();
        if (priceDetail.isWeekendEnabled() && isWeekend(date)) {
            result = priceDetail.getWeekend();
        }
        if (priceDetail.isHolidayEnabled() && isHoliday(date)) {
            result = priceDetail.getHoliday();
        }
        return Math.max(result, 0L);
    }

    private boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = DayOfWeek.of(date.get(ChronoField.DAY_OF_WEEK));
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }

    private boolean isHoliday(LocalDate date) {
        return false;
    }

    private Date parseDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
}
