package com.icezhg.sunflower.dao;


import com.icezhg.sunflower.domain.Inventory;
import com.icezhg.sunflower.pojo.InventoryDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2023/07/12.
 */
@Repository
public interface InventoryDao {

    int delete(Map<String, Object> params);

    int insert(Inventory record);

    int update(Inventory record);

    Inventory findById(Long id);

    Inventory findByResourceId(Long resourceId);

    List<Inventory> findByIds(List<Long> ids);

    List<Inventory> findAllOfType(Integer type);

    InventoryDetail findInventoryDetailById(Long id);

    int count(Map<String, Object> params);

    List<InventoryDetail> findInventoryDetails(Map<String, Object> params);

}
