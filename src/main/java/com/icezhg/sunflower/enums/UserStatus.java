package com.icezhg.sunflower.enums;

/**
 * Created by zhongjibing on 2023/02/23.
 */
public enum UserStatus {
    NORMAL(0, "正常"),
    LOCKED(1, "锁定"),
    ARCHIVED(2, "存档"),
    INCOMPLETE(6, "信息不完善");

    private final int status;
    private final String desc;

    UserStatus(int status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public int getStatus() {
        return status;
    }

    public String getDesc() {
        return desc;
    }
}
