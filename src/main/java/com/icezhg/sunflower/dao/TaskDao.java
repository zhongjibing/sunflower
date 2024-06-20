package com.icezhg.sunflower.dao;


import com.icezhg.sunflower.domain.Task;
import com.icezhg.sunflower.pojo.TaskInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TaskDao {

    int delete(Long id);

    int insert(Task record);

    TaskInfo findById(Long id);

    int update(Task record);

    int count(Map<String, Object> query);

    List<TaskInfo> find(Map<String, Object> query);

}
