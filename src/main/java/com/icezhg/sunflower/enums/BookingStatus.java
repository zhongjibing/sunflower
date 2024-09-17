package com.icezhg.sunflower.enums;

/**
 * Created by zhongjibing on 2023/08/03.
 */
public enum BookingStatus {
    DRAFT(0, "草稿"),
    TO_BE_CONFIRMED(1, "待确认"),
    PAYING(2, "待付款"),
    CONFIRMED(3, "已确认"),
    FINISHED(4, "已结束"),
    CANCELED(5, "已取消"),
    FULLY_BOOKED(9, "约满");


    private final int status;
    private final String name;

    BookingStatus(int status, String name) {
        this.status = status;
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }
}
