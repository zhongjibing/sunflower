package com.icezhg.sunflower.service;


import com.icezhg.sunflower.domain.TaskLog;
import com.icezhg.sunflower.pojo.TaskLogInfo;
import com.icezhg.sunflower.pojo.query.Query;

import java.util.List;

public interface TaskLogService {

    TaskLog addLog(TaskLog taskLog);

    int count(Query query);

    List<TaskLogInfo> find(Query query);
}
