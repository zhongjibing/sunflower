package com.icezhg.sunflower.server.entity;

import com.icezhg.sunflower.common.Num;
import com.icezhg.sunflower.util.Arith;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.lang.management.ManagementFactory;
import java.util.TimeZone;

/**
 * JVM相关信息
 */
public class Jvm {
    /**
     * 当前JVM占用的内存总数(M)
     */
    private double total;

    /**
     * JVM最大可用内存总数(M)
     */
    private double max;

    /**
     * JVM空闲内存(M)
     */
    private double free;

    /**
     * JDK版本
     */
    private String version;

    /**
     * JDK路径
     */
    private String home;

    public double getTotal() {
        return Arith.divide(total, (1024 * 1024), 2);
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getMax() {
        return Arith.divide(max, (1024 * 1024), 2);
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getFree() {
        return Arith.divide(free, (1024 * 1024), 2);
    }

    public void setFree(double free) {
        this.free = free;
    }

    public double getUsed() {
        return Arith.divide(total - free, (1024 * 1024), 2);
    }

    public double getUsage() {
        return Arith.multiply(Arith.divide(total - free, total, 4), 100);
    }

    /**
     * 获取JDK名称
     */
    public String getName() {
        return ManagementFactory.getRuntimeMXBean().getVmName();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    /**
     * JDK启动时间
     */
    public String getStartTime() {
        long startTime = ManagementFactory.getRuntimeMXBean().getStartTime();
        return DateFormatUtils.format(startTime, "yyyy-MM-dd HH:mm:ss z", TimeZone.getTimeZone("UTC"));
    }

    /**
     * JDK运行时间
     */
    public String getRunTime() {
        long delta = System.currentTimeMillis() - ManagementFactory.getRuntimeMXBean().getStartTime();
        long day = delta / Num.DAY_MILLIS;
        long hour = delta % Num.DAY_MILLIS / Num.HOUR_MILLIS;
        long min = delta % Num.DAY_MILLIS % Num.HOUR_MILLIS / Num.MINUTE_MILLIS;
        long sec = delta % Num.DAY_MILLIS % Num.HOUR_MILLIS % Num.MINUTE_MILLIS / Num.SECOND_MILLIS;
        StringBuilder builder = new StringBuilder();
        if (day > 0) {
            builder.append(day).append(" days ");
        }
        if (hour > 0) {
            builder.append(hour).append(" hours ");
        }
        if (min > 0) {
            builder.append(min).append(" minutes ");
        }
        builder.append(sec).append(" seconds.");

        return builder.toString();
    }

    /**
     * 运行参数
     */
    public String getInputArgs() {
        return String.join(", ", ManagementFactory.getRuntimeMXBean().getInputArguments());
    }
}
