package com.icezhg.sunflower.service.impl;

import com.icezhg.sunflower.dao.InventoryPlanDao;
import com.icezhg.sunflower.domain.Inventory;
import com.icezhg.sunflower.domain.InventoryPlan;
import com.icezhg.sunflower.service.InventoryPlanService;
import com.icezhg.sunflower.service.InventoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by zhongjibing on 2023/07/13.
 */
@Service
public class InventoryPlanServiceImpl implements InventoryPlanService {
    private static final Logger log = LoggerFactory.getLogger(InventoryPlanServiceImpl.class);

    private final InventoryPlanDao inventoryPlanDao;

    private InventoryService inventoryService;

    public InventoryPlanServiceImpl(InventoryPlanDao inventoryPlanDao) {
        this.inventoryPlanDao = inventoryPlanDao;
    }

    @Autowired
    public void setInventoryService(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @Override
    public void generate(Long inventoryId) {
        Inventory inventory = this.inventoryService.findById(inventoryId);
        if (inventory == null) {
            log.warn("inventory is not exist. id={}", inventoryId);
            return;
        }

        List<InventoryPlan> inventoryPlans = new LinkedList<>();
        LocalDate date = LocalDate.now(ZoneId.systemDefault());
        LocalDate nextMonth = LocalDate.now().plusMonths(1);
        while (date.isBefore(nextMonth)) {
            inventoryPlans.add(generateInventoryPlan(inventory, date));
            date = date.plusDays(1);
        }

        this.inventoryPlanDao.batchInsert(inventoryPlans);
    }

    @Override
    public void generate(Long inventoryId, LocalDate date) {
        Inventory inventory = this.inventoryService.findById(inventoryId);
        if (inventory == null) {
            log.warn("inventory is not exist. id={}", inventoryId);
            return;
        }

        this.inventoryPlanDao.batchInsert(List.of(generateInventoryPlan(inventory, date)));
    }

    @Override
    public void generateByType(Integer type) {
        List<Inventory> inventories = this.inventoryService.findAll(type);
        if (inventories.isEmpty()) {
            log.warn("inventories is empty. type={}", type);
            return;
        }
        List<InventoryPlan> inventoryPlans = new ArrayList<>(inventories.size());
        LocalDate date = LocalDate.now(ZoneId.systemDefault()).plusMonths(1);
        for (Inventory inventory : inventories) {
            inventoryPlans.add(generateInventoryPlan(inventory, date));
        }
        this.inventoryPlanDao.batchInsert(inventoryPlans);
    }

    private InventoryPlan generateInventoryPlan(Inventory inventory, LocalDate date) {
        InventoryPlan inventoryPlan = new InventoryPlan();
        inventoryPlan.setInventoryId(inventory.getId());
        inventoryPlan.setDate(parseDate(date));
        inventoryPlan.setResourceId(inventory.getResourceId());
        inventoryPlan.setType(inventory.getType());
        inventoryPlan.setTotal(inventory.getNumber());
        inventoryPlan.setUsed(0);
        inventoryPlan.setCreateTime(new Date());
        inventoryPlan.setUpdateTime(new Date());
        return inventoryPlan;
    }

    private Date parseDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
}
