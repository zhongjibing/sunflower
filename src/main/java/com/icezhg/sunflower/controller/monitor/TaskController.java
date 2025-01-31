package com.icezhg.sunflower.controller.monitor;

import com.icezhg.sunflower.annotation.Operation;
import com.icezhg.sunflower.common.Authority;
import com.icezhg.sunflower.enums.OperationType;
import com.icezhg.sunflower.pojo.PageResult;
import com.icezhg.sunflower.pojo.TaskInfo;
import com.icezhg.sunflower.pojo.query.TaskLogQuery;
import com.icezhg.sunflower.pojo.query.TaskQuery;
import com.icezhg.sunflower.service.TaskLogService;
import com.icezhg.sunflower.service.TaskService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    private final TaskLogService taskLogService;

    public TaskController(TaskService taskService, TaskLogService taskLogService) {
        this.taskService = taskService;
        this.taskLogService = taskLogService;
    }

    @PostMapping
    @Secured(Authority.Monitor.Task.ADD)
    @Operation(title = "tasks addition", type = OperationType.INSERT)
    public TaskInfo addTask(@RequestBody TaskInfo task) {
        return taskService.addTask(task);
    }

    @PutMapping
    @Secured(Authority.Monitor.Task.EDIT)
    @Operation(title = "tasks modification", type = OperationType.UPDATE)
    public TaskInfo updateTask(@RequestBody TaskInfo task) {
        return taskService.updateTask(task);
    }

    @GetMapping("/{taskId}")
    @Secured(Authority.Monitor.Task.QUERY)
    public TaskInfo taskInfo(@PathVariable Long taskId) {
        return taskService.findById(taskId);
    }

    @DeleteMapping("/{taskId}")
    @Secured(Authority.Monitor.Task.DELETE)
    @Operation(title = "tasks deletion", type = OperationType.DELETE)
    public void removeTask(@PathVariable Long taskId) {
        taskService.removeTask(taskId);
    }

    @GetMapping("/list")
    @Secured(Authority.Monitor.Task.QUERY)
    public PageResult list(TaskQuery query) {
        return new PageResult(taskService.count(query), taskService.find(query));
    }

    @PutMapping("/changeStatus")
    @Secured(Authority.Monitor.Task.STATUS)
    @Operation(title = "task status change", type = OperationType.UPDATE)
    public void changeTaskStatus(@RequestBody TaskInfo task) {
        taskService.changeTaskStatus(task);
    }

    @PostMapping("/run")
    @Secured(Authority.Monitor.Task.RUN)
    @Operation(title = "tasks execution", type = OperationType.EXECUTE)
    public void runTask(Long taskId) {
        taskService.runTask(taskId);
    }

    @GetMapping("/log/list")
    @Secured(Authority.Monitor.Task.LOGS)
    public PageResult listLogs(TaskLogQuery query) {
        return new PageResult(taskLogService.count(query), taskLogService.find(query));
    }

}
