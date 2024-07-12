package com.icezhg.sunflower.service;

import com.icezhg.sunflower.domain.Inventory;
import com.icezhg.sunflower.enums.ResourceType;
import com.icezhg.sunflower.pojo.InventoryDetail;
import com.icezhg.sunflower.pojo.InventoryInfo;
import com.icezhg.sunflower.pojo.query.Query;

import java.util.List;

/**
 * Created by zhongjibing on 2023/07/12.
 */
public interface InventoryService {
    InventoryInfo insert(InventoryInfo info, ResourceType resourceType);

    InventoryInfo update(InventoryInfo info, ResourceType resourceType);

    void deleteByIds(List<Long> ids);

    int count(Query query);

    List<Inventory> findAll(Integer type);

    List<InventoryDetail> findDetails(Query query);

    Inventory findById(Long id);

    InventoryDetail findDetailById(Long id);

    List<Inventory> findByIds(List<Long> ids);
}
