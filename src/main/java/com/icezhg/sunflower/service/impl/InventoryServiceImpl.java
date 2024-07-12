package com.icezhg.sunflower.service.impl;

import com.icezhg.commons.exception.InvalidParameterException;
import com.icezhg.sunflower.dao.InventoryDao;
import com.icezhg.sunflower.domain.Inventory;
import com.icezhg.sunflower.enums.ResourceType;
import com.icezhg.sunflower.event.InventoryUpdateEvent;
import com.icezhg.sunflower.pojo.InventoryDetail;
import com.icezhg.sunflower.pojo.InventoryInfo;
import com.icezhg.sunflower.pojo.query.DeleteQuery;
import com.icezhg.sunflower.pojo.query.Query;
import com.icezhg.sunflower.service.InventoryHistoryService;
import com.icezhg.sunflower.service.InventoryService;
import com.icezhg.sunflower.util.ApplicationContextUtil;
import com.icezhg.sunflower.util.CommonUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by zhongjibing on 2023/07/13.
 */
@Service
public class InventoryServiceImpl implements InventoryService {

    private final InventoryDao inventoryDao;

    private InventoryHistoryService inventoryHistoryService;

    public InventoryServiceImpl(InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    @Autowired
    public void setInventoryHistoryService(InventoryHistoryService inventoryHistoryService) {
        this.inventoryHistoryService = inventoryHistoryService;
    }

    @Override
    public InventoryInfo insert(InventoryInfo info, ResourceType resourceType) {
        Inventory inventory = buildInventory(info, resourceType);
        inventory.setId(null);
        checkUnique(inventory);
        CommonUtils.completeBaseInfo(inventory);
        this.inventoryDao.insert(inventory);
        ApplicationContextUtil.publishEvent(new InventoryUpdateEvent(inventory));
        return buildInventoryInfo(findById(inventory.getId()));
    }

    private void checkUnique(Inventory inventory) {
        Inventory existing = inventoryDao.findByResourceId(inventory.getResourceId());
        if (existing != null && (inventory.getId() == null || !inventory.getId().equals(existing.getId()))) {
            throw new InvalidParameterException("", "a record already exists with same resource");
        }
    }

    @Override
    public InventoryInfo update(InventoryInfo info, ResourceType resourceType) {
        Inventory inventory = buildInventory(info, resourceType);
        checkUnique(inventory);
        Inventory existing = findById(inventory.getId());
        if (existing == null) {
            throw new InvalidParameterException("", "parameter error");
        }

        CommonUtils.completeBaseInfo(inventory);
        this.inventoryDao.update(inventory);
        this.inventoryHistoryService.save(existing);
        if (inventory.getNumber() != null && !inventory.getNumber().equals(existing.getNumber())) {
            ApplicationContextUtil.publishEvent(new InventoryUpdateEvent(inventory));
        }
        return buildInventoryInfo(findById(inventory.getId()));
    }

    @Override
    public void deleteByIds(List<Long> ids) {
        if (CollectionUtils.isNotEmpty(ids)) {
            this.inventoryDao.delete(DeleteQuery.of(ids).toMap());
        }
    }

    @Override
    public int count(Query query) {
        return this.inventoryDao.count(query.toMap());
    }

    @Override
    public List<Inventory> findAll(Integer type) {
        return this.inventoryDao.findAllOfType(type);
    }

    @Override
    public List<InventoryDetail> findDetails(Query query) {
        return this.inventoryDao.findInventoryDetails(query.toMap());
    }

    @Override
    public Inventory findById(Long id) {
        return this.inventoryDao.findById(id);
    }

    @Override
    public InventoryDetail findDetailById(Long id) {
        return this.inventoryDao.findInventoryDetailById(id);
    }

    @Override
    public List<Inventory> findByIds(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return Collections.emptyList();
        }
        return this.inventoryDao.findByIds(ids);
    }

    private Inventory buildInventory(InventoryInfo info, ResourceType resourceType) {
        Inventory inventory = new Inventory();
        inventory.setId(info.getId());
        inventory.setResourceId(info.getResourceId());
        inventory.setType(resourceType.getType());
        inventory.setNumber(info.getNumber());
        inventory.setRemark(info.getRemark());
        return inventory;
    }

    private InventoryInfo buildInventoryInfo(Inventory inventory) {
        InventoryInfo info = new InventoryInfo();
        info.setId(inventory.getId());
        info.setResourceId(inventory.getResourceId());
        info.setNumber(inventory.getNumber());
        info.setRemark(inventory.getRemark());
        return info;
    }
}
