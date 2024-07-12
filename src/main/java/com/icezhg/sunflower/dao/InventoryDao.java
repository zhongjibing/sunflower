package com.icezhg.sunflower.dao;


import com.icezhg.sunflower.domain.Inventory;
import org.springframework.stereotype.Repository;

/**
 * Created by zhongjibing on 2023/07/12.
 */
@Repository
public interface InventoryDao {

    int delete(Long id);

    int insert(Inventory record);

    Inventory findById(Long id);

    int update(Inventory record);


}
