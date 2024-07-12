package com.icezhg.sunflower.service.impl;

import com.icezhg.sunflower.dao.InventoryHistoryDao;
import com.icezhg.sunflower.domain.Inventory;
import com.icezhg.sunflower.domain.InventoryHistory;
import com.icezhg.sunflower.service.InventoryHistoryService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by zhongjibing on 2023/07/12.
 */
@Service
public class InventoryHistoryServiceImpl implements InventoryHistoryService {

    private final InventoryHistoryDao inventoryHistoryDao;

    public InventoryHistoryServiceImpl(InventoryHistoryDao inventoryHistoryDao) {
        this.inventoryHistoryDao = inventoryHistoryDao;
    }

    @Override
    public void save(Inventory inventory) {
        this.inventoryHistoryDao.insert(buildInventoryHistory(inventory));
    }

    public InventoryHistory buildInventoryHistory(Inventory inventory) {
        InventoryHistory history = new InventoryHistory();
        history.setInventoryId(inventory.getId());
        history.setResourceId(inventory.getResourceId());
        history.setType(inventory.getType());
        history.setNumber(inventory.getNumber());
        history.setCreateTime(inventory.getCreateTime());
        history.setCreateBy(inventory.getCreateBy());
        history.setUpdateTime(inventory.getUpdateTime());
        history.setUpdateBy(inventory.getUpdateBy());
        history.setRemark(inventory.getRemark());
        history.setOperateTime(new Date());
        return history;
    }
}
