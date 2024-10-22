package com.icezhg.sunflower.service;


import com.icezhg.sunflower.domain.OperationLog;
import com.icezhg.sunflower.pojo.OperationInfo;
import com.icezhg.sunflower.pojo.query.Query;

import java.util.List;

/**
 * Created by zhongjibing on 2022/12/24.
 */
public interface OperationLogService {

    OperationLog addLog(OperationLog operationLog);

    int count(Query query);

    List<OperationInfo> find(Query query);
}
