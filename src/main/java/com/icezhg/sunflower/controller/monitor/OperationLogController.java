package com.icezhg.sunflower.controller.monitor;

import com.icezhg.sunflower.common.Authority;
import com.icezhg.sunflower.pojo.PageResult;
import com.icezhg.sunflower.pojo.query.OperationLogQuery;
import com.icezhg.sunflower.service.OperationLogService;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhongjibing on 2022/12/24.
 */
@RestController
@RequestMapping("/operation")
public class OperationLogController {

    private final OperationLogService operationLogService;

    public OperationLogController(OperationLogService operationLogService) {
        this.operationLogService = operationLogService;
    }

    @GetMapping("/list")
    @Secured(Authority.Log.Operation.QUERY)
    public PageResult listLogs(OperationLogQuery query) {
        return new PageResult(operationLogService.count(query), operationLogService.find(query));
    }
}
