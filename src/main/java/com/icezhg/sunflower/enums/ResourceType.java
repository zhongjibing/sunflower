package com.icezhg.sunflower.enums;

/**
 * Created by zhongjibing on 2023/06/28.
 */
public enum ResourceType {
    BANQUET_HALL(1, "宴会厅"),
    CONFERENCE_ROOM(2, "会议室"),
    GUEST_ROOM(3, "客房"),
    ;

    private final int type;
    private final String name;

    ResourceType(int type, String name) {
        this.type = type;
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }
}
