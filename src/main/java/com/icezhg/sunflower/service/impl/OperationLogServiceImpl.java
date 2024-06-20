package com.icezhg.sunflower.service.impl;


import com.icezhg.sunflower.dao.OperationLogDao;
import com.icezhg.sunflower.domain.OperationLog;
import com.icezhg.sunflower.pojo.OperationInfo;
import com.icezhg.sunflower.pojo.query.Query;
import com.icezhg.sunflower.service.OperationLogService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by zhongjibing on 2022/12/24.
 */
@Service
public class OperationLogServiceImpl implements OperationLogService {

    private final OperationLogDao operationLogDao;

    public OperationLogServiceImpl(OperationLogDao operationLogDao) {
        this.operationLogDao = operationLogDao;
    }

    @Override
    public OperationLog addLog(OperationLog operationLog) {
        if (operationLog.getCreateTime() == null) {
            operationLog.setCreateTime(new Date());
        }
        operationLogDao.insert(operationLog);
        return operationLog;
    }

    @Override
    public int count(Query query) {
        return operationLogDao.count(query.toMap());
    }

    @Override
    public List<OperationInfo> find(Query query) {
        return operationLogDao.find(query.toMap());
    }
}
