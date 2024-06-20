package com.icezhg.sunflower.service.impl;

import com.icezhg.sunflower.dao.TaskLogDao;
import com.icezhg.sunflower.domain.TaskLog;
import com.icezhg.sunflower.pojo.TaskLogInfo;
import com.icezhg.sunflower.pojo.query.Query;
import com.icezhg.sunflower.service.TaskLogService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskLogServiceImpl implements TaskLogService {

    private final TaskLogDao taskLogDao;

    public TaskLogServiceImpl(TaskLogDao taskLogDao) {
        this.taskLogDao = taskLogDao;
    }

    @Override
    public TaskLog addLog(TaskLog taskLog) {
        if (taskLog.getCreateTime() == null) {
            taskLog.setCreateTime(new Date());
        }
        taskLogDao.insert(taskLog);
        return taskLog;
    }

    @Override
    public int count(Query query) {
        return taskLogDao.count(query.toMap());
    }

    @Override
    public List<TaskLogInfo> find(Query query) {
        return taskLogDao.find(query.toMap());
    }
}
