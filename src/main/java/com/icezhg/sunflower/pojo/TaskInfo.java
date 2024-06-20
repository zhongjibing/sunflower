package com.icezhg.sunflower.pojo;


import com.icezhg.sunflower.domain.Task;
import lombok.Data;
import org.quartz.CronExpression;

import java.text.ParseException;
import java.util.Date;

@Data
public class TaskInfo {
    /**
     * 任务ID
     */
    private Long id;

    /**
     * 任务名称
     */
    private String taskName;

    /**
     * 任务组名
     */
    private String taskGroup;

    /**
     * 调用目标字符串
     */
    private String invokeTarget;

    /**
     * cron执行表达式
     */
    private String cronExpression;

    /**
     * 计划执行错误策略（1立即执行 2执行一次 3放弃执行）
     */
    private String misfirePolicy;

    /**
     * 是否并发执行（0允许 1禁止）
     */
    private String concurrent;

    /**
     * 状态（0正常 1暂停）
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 备注
     */
    private String remark;

    public Date getNextFireTime() {
        if (cronExpression != null) {
            try {
                return new CronExpression(cronExpression).getNextValidTimeAfter(new Date());
            } catch (ParseException ignored) {
            }
        }
        return null;
    }

    public Task toTask() {
        Task task = new Task();
        task.setId(this.id);
        task.setTaskName(this.taskName);
        task.setTaskGroup(this.taskGroup);
        task.setInvokeTarget(this.invokeTarget);
        task.setCronExpression(this.cronExpression);
        task.setMisfirePolicy(this.misfirePolicy);
        task.setConcurrent(this.concurrent);
        task.setStatus(this.status);
        task.setRemark(this.remark);
        return task;
    }
}
