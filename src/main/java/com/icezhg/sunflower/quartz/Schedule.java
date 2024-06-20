package com.icezhg.sunflower.quartz;

public interface Schedule {

    /** 执行目标key */
    String TASK_ID = "taskId";

    /** 默认 */
    String MISFIRE_DEFAULT = "0";

    /** 立即触发执行 */
    String MISFIRE_IGNORE_MISFIRES = "1";

    /** 触发一次执行 */
    String MISFIRE_FIRE_AND_PROCEED = "2";

    /** 不触发立即执行 */
    String MISFIRE_DO_NOTHING = "3";

    /** 不允许并发执行 */
    String DISALLOWED_CONCURRENT = "1";

    /** 暂停状态 */
    String STATUS_PAUSE = "1";
}
