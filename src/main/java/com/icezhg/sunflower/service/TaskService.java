package com.icezhg.sunflower.service;

import com.icezhg.sunflower.pojo.TaskInfo;
import com.icezhg.sunflower.pojo.query.Query;

import java.util.List;

public interface TaskService {

    TaskInfo findById(Long id);

    int count(Query query);

    List<TaskInfo> find(Query query);

    TaskInfo addTask(TaskInfo taskInfo);

    TaskInfo updateTask(TaskInfo taskInfo);

    void removeTask(Long id);

    void changeTaskStatus(TaskInfo taskInfo);

    void runTask(Long id);
}
