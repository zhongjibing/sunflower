package com.icezhg.sunflower.dao;


import com.icezhg.sunflower.domain.TaskLog;
import com.icezhg.sunflower.pojo.TaskLogInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TaskLogDao {

    int insert(TaskLog record);

    int count(Map<String, Object> query);

    List<TaskLogInfo> find(Map<String, Object> query);

}
