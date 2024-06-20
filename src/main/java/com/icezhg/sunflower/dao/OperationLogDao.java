package com.icezhg.sunflower.dao;

import com.icezhg.sunflower.domain.OperationLog;
import com.icezhg.sunflower.pojo.OperationInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhongjibing on 2022/12/24.
 */
@Repository
public interface OperationLogDao {

    int insert(OperationLog operationLog);

    int count(Map<String, Object> query);

    List<OperationInfo> find(Map<String, Object> query);
}
