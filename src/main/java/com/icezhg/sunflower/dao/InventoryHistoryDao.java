package com.icezhg.sunflower.dao;

import com.icezhg.sunflower.domain.InventoryHistory;
import org.springframework.stereotype.Repository;

/**
 * Created by zhongjibing on 2023/07/12.
 */
@Repository
public interface InventoryHistoryDao {

    int insert(InventoryHistory record);

}
