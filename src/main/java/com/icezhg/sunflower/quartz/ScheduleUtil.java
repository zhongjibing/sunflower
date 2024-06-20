package com.icezhg.sunflower.quartz;


import com.icezhg.commons.exception.ErrorCodeException;
import com.icezhg.sunflower.pojo.TaskInfo;
import com.icezhg.sunflower.quartz.job.MethodInvokingJob;
import com.icezhg.sunflower.quartz.job.StatefulMethodInvokingJob;
import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ScheduleUtil implements Schedule {

    private static Scheduler scheduler;

    @Autowired
    public void setScheduler(Scheduler scheduler) {
        ScheduleUtil.scheduler = scheduler;
    }

    public static void create(TaskInfo taskInfo) {
        JobDetail jobDetail = JobBuilder.newJob(getQuartzJobClass(taskInfo))
                .usingJobData(TASK_ID, taskInfo.getId())
                .withIdentity(jobKey(taskInfo))
                .build();

        CronScheduleBuilder cronSchedule = CronScheduleBuilder.cronSchedule(taskInfo.getCronExpression());
        if (StringUtils.equals(taskInfo.getMisfirePolicy(), MISFIRE_IGNORE_MISFIRES)) {
            cronSchedule.withMisfireHandlingInstructionIgnoreMisfires();
        } else if (StringUtils.equals(taskInfo.getMisfirePolicy(), MISFIRE_FIRE_AND_PROCEED)) {
            cronSchedule.withMisfireHandlingInstructionFireAndProceed();
        } else {
            cronSchedule.withMisfireHandlingInstructionDoNothing();
        }

        CronTrigger trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withSchedule(cronSchedule)
                .withIdentity(triggerKey(taskInfo))
                .build();

        try {
            if (scheduler.checkExists(jobDetail.getKey())) {
                scheduler.pauseJob(jobDetail.getKey());
                scheduler.deleteJob(jobDetail.getKey());
            }

            scheduler.scheduleJob(jobDetail, trigger);

            if (StringUtils.equals(taskInfo.getStatus(), STATUS_PAUSE)) {
                scheduler.pauseJob(jobDetail.getKey());
            }
        } catch (SchedulerException e) {
            throw new ErrorCodeException("", e.getMessage(), e);
        }

    }

    public static void pause(TaskInfo taskInfo) {
        try {
            scheduler.pauseJob(jobKey(taskInfo));
        } catch (SchedulerException e) {
            throw new ErrorCodeException("", e.getMessage(), e);
        }
    }

    public static void restart(TaskInfo taskInfo) {
        try {
            scheduler.resumeJob(jobKey(taskInfo));
        } catch (SchedulerException e) {
            throw new ErrorCodeException("", e.getMessage(), e);
        }
    }

    public static void runOnce(TaskInfo taskInfo) {
        try {
            scheduler.triggerJob(jobKey(taskInfo));
        } catch (SchedulerException e) {
            throw new ErrorCodeException("", e.getMessage(), e);
        }
    }

    public static void remove(TaskInfo taskInfo) {
        try {
            scheduler.deleteJob(jobKey(taskInfo));
        } catch (SchedulerException e) {
            throw new ErrorCodeException("", e.getMessage(), e);
        }
    }

    public static boolean checkExists(TaskInfo taskInfo) {
        try {
            return scheduler.checkExists(jobKey(taskInfo));
        } catch (SchedulerException e) {
            throw new ErrorCodeException("", e.getMessage(), e);
        }
    }

    private static Class<? extends Job> getQuartzJobClass(TaskInfo taskInfo) {
        if (StringUtils.equals(taskInfo.getConcurrent(), DISALLOWED_CONCURRENT)) {
            return StatefulMethodInvokingJob.class;
        }
        return MethodInvokingJob.class;
    }

    private static JobKey jobKey(TaskInfo taskInfo) {
        return JobKey.jobKey(taskInfo.getId() + "-" + taskInfo.getTaskName(), taskInfo.getTaskGroup());
    }

    private static TriggerKey triggerKey(TaskInfo taskInfo) {
        return TriggerKey.triggerKey(taskInfo.getId() + "-" + taskInfo.getTaskName(), taskInfo.getTaskGroup());
    }

}
