package com.icezhg.sunflower.service.impl;


import com.icezhg.sunflower.entity.OperationLog;
import com.icezhg.sunflower.pojo.query.Query;
import com.icezhg.sunflower.repository.OperationLogRepository;
import com.icezhg.sunflower.service.OperationLogService;
import org.apache.tomcat.util.modeler.OperationInfo;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zhongjibing on 2022/12/24.
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {

    private final OperationLogRepository operationLogRepository;

    public OperationLogServiceImpl(OperationLogRepository operationLogRepository) {
        this.operationLogRepository = operationLogRepository;
    }

    @Override
    public OperationLog addLog(OperationLog operationLog) {
        if (operationLog.getCreateTime() == null) {
            operationLog.setCreateTime(new Date());
        }
        operationLogRepository.save(operationLog);
        return operationLog;
    }

    @Override
    public int count(Query query) {
        return operationLogRepository.count(query.toMap());
    }

    @Override
    public List<OperationInfo> find(Query query) {
        return operationLogRepository.find(query.toMap());
    }
}
