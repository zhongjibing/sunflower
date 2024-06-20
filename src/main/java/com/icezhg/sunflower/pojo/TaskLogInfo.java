package com.icezhg.sunflower.pojo;


import lombok.Data;

import java.util.Date;

@Data
public class TaskLogInfo {
    /**
     * 任务日志ID
     */
    private Long id;

    /**
     * 任务id
     */
    private Long taskId;

    /**
     * 调用目标字符串
     */
    private String invokeTarget;

    /**
     * 日志信息
     */
    private String message;

    /**
     * 执行状态（0正常 1失败）
     */
    private String status;

    /**
     * 异常信息
     */
    private String exceptionInfo;

    /**
     * 执行开始时间
     */
    private Date startTime;

    /**
     * 执行结束时间
     */
    private Date stopTime;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务组名
     */
    private String taskGroup;
}
